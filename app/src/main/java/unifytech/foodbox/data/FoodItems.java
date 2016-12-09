package unifytech.foodbox.data;

import java.util.ArrayList;
import java.util.List;

public class FoodItems {

    public static final List<FoodItem> VEG_ITEMS = new ArrayList<>();
    public static final List<FoodItem> NONVEG_ITEMS = new ArrayList<>();
    public static final List<FoodItem> RECOMMENDED_ITEMS = new ArrayList<>();
    public static final List<FoodItem> SNACKS_ITEMS = new ArrayList<>();


    /*  private static String makeDetails(int position) {
          StringBuilder builder = new StringBuilder();
          builder.append("Details about Item: ").append(position);
          for (int i = 0; i < position; i++) {
              builder.append("\nMore details information here.");
          }
          return builder.toString();
      }
  */
    public static class FoodItem {
        public String title, item_tag, thumbnailUrl, expiry_time, location, issnacks, concat, isrecommded;
        public int id, mrp, master_id;
        public boolean veg;

      /*  public FoodItem(String title, String thumbnailUrl) {
            this.title = title;
            this.thumbnailUrl = thumbnailUrl;
        }*/

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getItem_tag() {
            return item_tag;
        }

        public void setItem_tag(String item_tag) {
            this.item_tag = item_tag;
        }

        public String getThumbnailUrl() {
            return thumbnailUrl;
        }

        public void setThumbnailUrl(String thumbnailUrl) {
            this.thumbnailUrl = thumbnailUrl;
        }

        public String getExpiry_time() {
            return expiry_time;
        }

        public void setExpiry_time(String expiry_time) {
            this.expiry_time = expiry_time;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getIssnacks() {
            return issnacks;
        }

        public void setIssnacks(String issnacks) {
            this.issnacks = issnacks;
        }

        public String getConcat() {
            return concat;
        }

        public void setConcat(String concat) {
            this.concat = concat;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getMrp() {
            return mrp;
        }

        public void setMrp(int mrp) {
            this.mrp = mrp;
        }

        public int getMaster_id() {
            return master_id;
        }

        public void setMaster_id(int master_id) {
            this.master_id = master_id;
        }

        public boolean isVeg() {
            return veg;
        }

        public void setVeg(boolean veg) {
            this.veg = veg;
        }

        public String isrecommded() {
            return isrecommded;
        }

        public void setIsrecommded(String isrecommded) {
            this.isrecommded = isrecommded;
        }

        @Override
        public String toString() {
            return title;
        }
    }
}
