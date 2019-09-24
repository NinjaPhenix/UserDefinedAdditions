package ninjaphenix.userdefinedadditions.mixins;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.LevelProperties;
import ninjaphenix.userdefinedadditions.mixins.accessors.MinecraftServerAccessor;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.File;
import java.nio.file.Path;

@Mixin(MinecraftServer.class)
@Interface(iface = MinecraftServerAccessor.class, prefix = "userdefinedadditions$")
public class MinecraftServerMixin implements MinecraftServerAccessor
{
    private Path datapackDir;

    @Override
    public Path getDatapackDirectory()
    {
        return datapackDir;
    }

    @Inject(method = "loadWorldDataPacks(Ljava/io/File;Lnet/minecraft/world/level/LevelProperties;)V", at = @At("TAIL"))
    private void loadWorldDataPacks(File file, LevelProperties properties, CallbackInfo ci)
    {
        datapackDir = file.toPath().resolve("datapacks");
        System.out.println();
    }
}

