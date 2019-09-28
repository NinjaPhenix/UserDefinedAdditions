package ninjaphenix.userdefinedadditions.readers;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import net.minecraft.util.Identifier;
import ninjaphenix.userdefinedadditions.readers.interfaces.Reader;

import java.util.function.Supplier;

public class ReaderManager
{
    private static final ReaderManager INSTANCE = new ReaderManager();

    private final Table<Identifier, Integer, Reader<? extends Supplier<?>, ?>> serializers;

    private ReaderManager()
    {
        serializers = HashBasedTable.create();
    }

    public static ReaderManager getInstance() { return INSTANCE; }

    public <T> void registerSerializerType(Identifier id, int version, Reader<? extends Supplier<T>, T> serializer)
    {
        serializers.put(id, version, serializer);

    }

    public <T> Reader<? extends Supplier<T>, T> getSerializer(Identifier id, int version)
    {
        //noinspection unchecked
        return (Reader<? extends Supplier<T>, T>) serializers.get(id, version);
    }

}
