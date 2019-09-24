package ninjaphenix.userdefinedadditions.serializers.interfaces;

import net.minecraft.util.Identifier;

import java.util.function.Supplier;

public interface RegistrableSupplier<T> extends Supplier<T>
{
    T register(Identifier id);
}
