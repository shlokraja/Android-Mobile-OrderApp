package unifytech.foodbox.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DataItems {

    public static final List<DataItem> ITEMS = new ArrayList<DataItem>();

    /**
     * A map of sample (utils) items, by ID.
     */
    public static final Map<String, DataItem> ITEM_MAP = new HashMap<String, DataItem>();


    private static void addItem(DataItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.title, item);
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A utils item representing a piece of content.
     */
    public static class DataItem {
        public final String title;
        public final String thumbnailUrl;
        public final int restaurentId;

        public DataItem(String title, String thumbnailUrl, int restaurentId) {
            this.title = title;
            this.thumbnailUrl = thumbnailUrl;
            this.restaurentId = restaurentId;
        }

        @Override
        public String toString() {
            return title;
        }
    }
}
