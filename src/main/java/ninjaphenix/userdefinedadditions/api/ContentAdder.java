package ninjaphenix.userdefinedadditions.api;

public interface ContentAdder
{
    void addReaders(ReaderManager manager);

    default void addLoadOrderValues(LoadOrderManager manager) {}
}
