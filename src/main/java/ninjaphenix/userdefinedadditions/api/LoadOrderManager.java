package ninjaphenix.userdefinedadditions.api;

import java.text.MessageFormat;
import java.util.Iterator;
import java.util.LinkedList;

@SuppressWarnings("WeakerAccess")
public class LoadOrderManager
{
    private static final LoadOrderManager INSTANCE = new LoadOrderManager();

    private final LinkedList<String> loadOrder;

    private LoadOrderManager() { loadOrder = new LinkedList<>(); }

    public static LoadOrderManager getInstance() { return INSTANCE; }

    public void addAfter(String folder_name, String after) { add(1, folder_name, after); }

    public void addBefore(String folder_name, String before) { add(0, folder_name, before); }

    private void add(int offset, String folder_name, String value)
    {
        if (loadOrder.contains(value))
        {
            if (!loadOrder.contains(folder_name))
            {
                int index = loadOrder.indexOf(value) + offset;
                if (index <= loadOrder.size()) loadOrder.add(index, folder_name);
                else loadOrder.add(folder_name);
            }
            else throw new RuntimeException(MessageFormat.format("Tried adding duplicate value: {0}.", folder_name));
        }
        else throw new RuntimeException(MessageFormat.format("Load order {0} does not exist.", value));
    }

    public void addEnd(String folder_name)
    {
        if (!loadOrder.contains(folder_name)) loadOrder.addLast(folder_name);
        else throw new RuntimeException(MessageFormat.format("Tried adding duplicate value: {0}.", folder_name));
    }

    public boolean contains(String folder_name) { return loadOrder.contains(folder_name); }

    public Iterator<String> iterator() { return loadOrder.iterator(); }
}
