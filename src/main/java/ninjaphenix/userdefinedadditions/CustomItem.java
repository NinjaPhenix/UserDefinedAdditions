package ninjaphenix.userdefinedadditions;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;

public class CustomItem extends Item
{
    private final Formatting formatting;

    public CustomItem(Settings settings, Formatting formatting)
    {
        super(settings);
        this.formatting = formatting;
    }

    @Override
    public Text getName(ItemStack stack) { return new TranslatableText(getTranslationKey(stack)).formatted(formatting); }
}
