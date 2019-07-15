package ninjaphenix.userdefinedadditions;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;

public class CustomItem extends Item
{
    private final Formatting fontColor;

    public CustomItem(Settings settings, Formatting fontColor)
    {
        super(settings);
        this.fontColor = fontColor;
    }

    @Override
    public Text getName(ItemStack stack) { return new TranslatableText(getTranslationKey(stack)).formatted(fontColor); }
}
