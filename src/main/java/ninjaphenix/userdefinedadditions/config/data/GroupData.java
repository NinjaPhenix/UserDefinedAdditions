package ninjaphenix.userdefinedadditions.config.data;

import blue.endless.jankson.JsonObject;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;
import net.minecraft.util.registry.Registry;
import ninjaphenix.userdefinedadditions.CommonEntry;
import ninjaphenix.userdefinedadditions.config.Config;

public final class GroupData
{
    private final String identifier;
    private final String icon;

    private GroupData(String identifier, String icon)
    {
        this.identifier = identifier;
        this.icon = icon;
    }

    public static GroupData parse(JsonObject object)
    {
        String identifier = object.get(String.class, "identifier");
        String icon = object.get(String.class, "icon");
        return new GroupData(identifier, icon);
    }

    public Pair<Identifier, ItemGroup> asMCObject()
    {
        Identifier groupName;
        Identifier groupIcon;
        try
        {
            if (identifier.contains(":")) { groupName = new Identifier(identifier); }
            else { groupName = Config.INSTANCE.getId(identifier); }
        }
        catch (Exception e)
        {
            CommonEntry.LOGGER.warn("[{}] Failed to create item group with identifier: {}, cannot create Identifier.", CommonEntry.MOD_ID, identifier);
            return null;
        }
        try
        {
            if (icon.contains(":")) { groupIcon = new Identifier(icon); }
            else { groupIcon = Config.INSTANCE.getId(icon); }
        }
        catch (Exception e)
        {
            CommonEntry.LOGGER.warn("[{}] Failed to create item group with icon: {}, cannot create Identifier.", CommonEntry.MOD_ID, icon);
            return null;
        }
        return new Pair(groupName, FabricItemGroupBuilder.create(groupName).icon(() -> new ItemStack(Registry.ITEM.get(groupIcon))).build());
    }
}
