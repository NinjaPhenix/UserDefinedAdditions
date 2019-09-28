package ninjaphenix.userdefinedadditions.api;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import net.minecraft.util.Identifier;
import ninjaphenix.userdefinedadditions.readers.interfaces.Reader;

public class ReaderManager
{
    private static final ReaderManager INSTANCE = new ReaderManager();

    private final Table<Identifier, Integer, Reader<?>> readers;

    private ReaderManager() { readers = HashBasedTable.create(); }

    public static ReaderManager getInstance() { return INSTANCE; }

    public <T> void add(Identifier id, int version, Reader<T> reader) { readers.put(id, version, reader); }

    @SuppressWarnings("unchecked")
    public <T> Reader<T> get(Identifier id, int version) { return (Reader<T>) readers.get(id, version); }
}
