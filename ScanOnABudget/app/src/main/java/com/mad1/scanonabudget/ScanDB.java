package com.mad1.scanonabudget;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.location.Location;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ScanDB extends SQLiteOpenHelper {
    private final String TABLE_ITEM = "item";
    private static String ITEM_ID = "item_id";
    private static String ITEM_NAME = "name";
    private static String ITEM_DESCRIPTION = "description";
    private static String ITEM_PRICE = "price";
    private static String STORE_NAME = "store";
    private static String BARCODE = "barcode";
    private static String LAT = "lat";
    private static String LON = "lon";

    private final String TABLE_LIST = "list";
    private final String LIST_ID = "list_id";
    private final String LIST_ITEM_NAME = "name";
    private final String LIST_ITEM_PRICE = "price";
    private final String LIST_ITEM_STORE = "store";
    private Context Ctx;
    List<ListTable> ListTableList = new ArrayList<>();

    public ScanDB(Context context) {
        super(context, "Scan.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ITEM_TABLE = "CREATE TABLE " + TABLE_ITEM + "("
                + ITEM_ID + " INTEGER UNIQUE PRIMARY KEY AUTOINCREMENT,"
                + ITEM_NAME + " TEXT,"
                + ITEM_DESCRIPTION + " TEXT, " +
                ITEM_PRICE + " INTEGER," +
                BARCODE + " INTEGER, " +
                STORE_NAME + " TEXT, " +
                LAT + " INTEGER, " +
                LON + " INTEGER" + ")";

        String CREATE_lIST_TABLE = "CREATE TABLE " + TABLE_LIST + "("
                + LIST_ID + " INTEGER UNIQUE PRIMARY KEY AUTOINCREMENT,"
                + LIST_ITEM_NAME + " TEXT UNIQUE," +
                LIST_ITEM_PRICE + " INTEGER," +
                LIST_ITEM_STORE + " TEXT" + ")";

        db.execSQL(CREATE_ITEM_TABLE);
        db.execSQL(CREATE_lIST_TABLE);

        db.execSQL("INSERT INTO item VALUES (1, 'White Grapes', 'Seedless', 1.2, 20250263, 'Lidl', 52, -8)");
        db.execSQL("INSERT INTO item VALUES (2, 'White Grapes', 'Seedless', 2, 20250263, 'Dunnes', 52, -8)");
        db.execSQL("INSERT INTO item VALUES (3, 'White Grapes', 'Seedless', 1, 20250263, 'Tesco', 52, -8)");
        db.execSQL("INSERT INTO item VALUES (4, 'White Grapes', 'Seedless', .90, 20250263, 'Spar', 53, -8)");
        db.execSQL("INSERT INTO item VALUES (5, 'White Grapes', 'Seedless', 2.45, 20250263, 'Centra', 53, -8)");

        db.execSQL("INSERT INTO item VALUES (6, 'Sprite', 'Lemon-Lime No Sugar', .70 , 5449000165695, 'Lidl', 52, -8)");
        db.execSQL("INSERT INTO item VALUES (7, 'Sprite', 'Lemon-Lime No Sugar', 1.30, 5449000165695, 'Dunnes', 52, -8)");
        db.execSQL("INSERT INTO item VALUES (8, 'Sprite', 'Lemon-Lime No Sugar', 1, 5449000165695, 'Tesco', 52, -8)");
        db.execSQL("INSERT INTO item VALUES (9, 'Sprite', 'Lemon-Lime No Sugar', 1, 5449000165695, 'Spar', 53, -3)");
        db.execSQL("INSERT INTO item VALUES (10, 'Sprite', 'Lemon-Lime No Sugar', 1.90, 5449000165695, 'Centra', 53, -3)");

        db.execSQL("INSERT INTO item VALUES (11, 'Two Pack Pepers', '', .90 , 10095454, 'Lidl', 52, -8)");
        db.execSQL("INSERT INTO item VALUES (12, 'Two Pack Pepers', '', 1.50, 10095454, 'Dunnes', 52, -8)");
        db.execSQL("INSERT INTO item VALUES (13, 'Two Pack Pepers', '', 1.20, 10095454, 'Tesco', 52, -8)");
        db.execSQL("INSERT INTO item VALUES (14, 'Two Pack Pepers', '', 1.10, 10095454, 'Spar', 53, -3)");
        db.execSQL("INSERT INTO item VALUES (15, 'Two Pack Pepers', '', 1.19, 10095454, 'Centra', 53, -3)");

        db.execSQL("INSERT INTO item VALUES (16, 'Sweet Potatoes', 'A rich, vibrant potato ideal for mashing, rasting or as wedges', 1 , 5057545165713, 'Lidl', 52, -8)");
        db.execSQL("INSERT INTO item VALUES (17, 'Sweet Potatoes', 'A rich, vibrant potato ideal for mashing, rasting or as wedges', 1.50, 5057545165713, 'Dunnes', 52, -8)");
        db.execSQL("INSERT INTO item VALUES (18, 'Sweet Potatoes', 'A rich, vibrant potato ideal for mashing, rasting or as wedges', 2, 5057545165713, 'Tesco', 52, -8)");
        db.execSQL("INSERT INTO item VALUES (19, 'Sweet Potatoes', 'A rich, vibrant potato ideal for mashing, rasting or as wedges', 2.20, 5057545165713, 'Spar', 53, -3)");
        db.execSQL("INSERT INTO item VALUES (20, 'Sweet Potatoes', 'A rich, vibrant potato ideal for mashing, rasting or as wedges', 2.26, 5057545165713, 'Centra', 53, -3)");

        db.execSQL("INSERT INTO item VALUES (21, 'Irish Parsnips', 'Carefully harvested and great for roasting or mash', 1.10 , 10054758, 'Lidl', 52, -8)");
        db.execSQL("INSERT INTO item VALUES (22, 'Irish Parsnips', 'Carefully harvested and great for roasting or mash', 1.65, 10054758, 'Dunnes', 52, -8)");
        db.execSQL("INSERT INTO item VALUES (23, 'Irish Parsnips', 'Carefully harvested and great for roasting or mash', .90, 10054758, 'Tesco', 52, -8)");
        db.execSQL("INSERT INTO item VALUES (24, 'Irish Parsnips', 'Carefully harvested and great for roasting or mash', 1.90, 10054758, 'Spar', 53, -3)");
        db.execSQL("INSERT INTO item VALUES (25, 'Irish Parsnips', 'Carefully harvested and great for roasting or mash', 1.46, 10054758, 'Centra', 53, -3)");

        db.execSQL("INSERT INTO item VALUES (26, 'Corn Cakes', 'Bunalun Organic', 1.10 , 5391500914559, 'Lidl', 52, -8)");
        db.execSQL("INSERT INTO item VALUES (27, 'Corn Cakes', 'Bunalun Organic', .80, 5391500914559, 'Dunnes', 52, -8)");
        db.execSQL("INSERT INTO item VALUES (28, 'Corn Cakes', 'Bunalun Organic', .90, 5391500914559, 'Tesco', 52, -8)");
        db.execSQL("INSERT INTO item VALUES (29, 'Corn Cakes', 'Bunalun Organic', 2.10, 5391500914559, 'Spar', 53, -3)");
        db.execSQL("INSERT INTO item VALUES (30, 'Corn Cakes', 'Bunalun Organic', 2.13, 5391500914559, 'Centra', 53, -3)");

        db.execSQL("INSERT INTO item VALUES (31, 'Stir fry sauce', 'Amoy Stir Fry Sauce hoi sin', 1.15 , 5000111046862, 'Lidl', 52, -8)");
        db.execSQL("INSERT INTO item VALUES (32, 'Stir fry sauce', 'Amoy Stir Fry Sauce hoi sin', 1.20, 5000111046862, 'Dunnes', 52, -8)");
        db.execSQL("INSERT INTO item VALUES (33, 'Stir fry sauce', 'Amoy Stir Fry Sauce hoi sin', 1.10, 5000111046862, 'Tesco', 52, -8)");
        db.execSQL("INSERT INTO item VALUES (34, 'Stir fry sauce', 'Amoy Stir Fry Sauce hoi sin', 1.17, 5000111046862, 'Spar', 53, -3)");
        db.execSQL("INSERT INTO item VALUES (35, 'Stir fry sauce', 'Amoy Stir Fry Sauce hoi sin', 1.47, 5000111046862, 'Centra', 53, -3)");

        db.execSQL("INSERT INTO item VALUES (36, 'Dark soy sauce', 'Blue Dragon', 1.30 , 5010338404369, 'Lidl', 52, -8)");
        db.execSQL("INSERT INTO item VALUES (37, 'Dark soy sauce', 'Blue Dragon', 1.50, 5010338404369, 'Dunnes', 52, -8)");
        db.execSQL("INSERT INTO item VALUES (38, 'Dark soy sauce', 'Blue Dragon', 1.20, 5010338404369, 'Tesco', 52, -8)");
        db.execSQL("INSERT INTO item VALUES (39, 'Dark soy sauce', 'Blue Dragon', 1.40, 5010338404369, 'Spar', 53, -3)");
        db.execSQL("INSERT INTO item VALUES (40, 'Dark soy sauce', 'Blue Dragon', 1.62, 5010338404369, 'Centra', 53, -3)");

        db.execSQL("INSERT INTO item VALUES (41, 'Spiced Apple and pear chutney', 'Duluxe', 1.05 , 4056489172956, 'Lidl', 52, -8)");
        db.execSQL("INSERT INTO item VALUES (42, 'Spiced Apple and pear chutney', 'Duluxe', 1.70, 4056489172956, 'Dunnes', 52, -8)");
        db.execSQL("INSERT INTO item VALUES (43, 'Spiced Apple and pear chutney', 'Duluxe', 1.10, 4056489172956, 'Tesco', 52, -8)");
        db.execSQL("INSERT INTO item VALUES (44, 'Spiced Apple and pear chutney', 'Duluxe', 1.30, 4056489172956, 'Spar', 53, -3)");
        db.execSQL("INSERT INTO item VALUES (45, 'Spiced Apple and pear chutney', 'Duluxe', 1.80, 4056489172956, 'Centra', 53, -3)");

        db.execSQL("INSERT INTO item VALUES (46, 'Olive Oil', 'Made from 100% olives checked for flawless taste', 1.20 , 5000119421210, 'Lidl', 52, -8)");
        db.execSQL("INSERT INTO item VALUES (47, 'Olive Oil', 'Made from 100% olives checked for flawless taste', 1.60, 5000119421210, 'Dunnes', 52, -8)");
        db.execSQL("INSERT INTO item VALUES (48, 'Olive Oil', 'Made from 100% olives checked for flawless taste', 1.30, 5000119421210, 'Tesco', 52, -8)");
        db.execSQL("INSERT INTO item VALUES (49, 'Olive Oil', 'Made from 100% olives checked for flawless taste', 1.60, 5000119421210, 'Spar', 53, -3)");
        db.execSQL("INSERT INTO item VALUES (50, 'Olive Oil', 'Made from 100% olives checked for flawless taste', 1.57, 5000119421210, 'Centra', 53, -3)");

        db.execSQL("INSERT INTO item VALUES (51, 'Sweet Chilli Sauce', '', 1.20 , 8850344040130, 'Lidl', 52, -8)");
        db.execSQL("INSERT INTO item VALUES (52, 'Sweet Chilli Sauce', '', 1.60, 8850344040130, 'Dunnes', 52, -8)");
        db.execSQL("INSERT INTO item VALUES (53, 'Sweet Chilli Sauce', '', 1.30, 8850344040130, 'Tesco', 52, -8)");
        db.execSQL("INSERT INTO item VALUES (54, 'Sweet Chilli Sauce', '', 1.80, 8850344040130, 'Spar', 53, -3)");
        db.execSQL("INSERT INTO item VALUES (55, 'Sweet Chilli Sauce', '', 1.30, 8850344040130, 'Centra', 53, -3)");

        db.execSQL("INSERT INTO item VALUES (56, 'Tomato Ketchup', 'Made with sun-ripened tomatoes for a vibrant flavour', 1.03 , 5057373701930, 'Lidl', 52, -8)");
        db.execSQL("INSERT INTO item VALUES (57, 'Tomato Ketchup', 'Made with sun-ripened tomatoes for a vibrant flavour', 1.80, 5057373701930, 'Dunnes', 52, -8)");
        db.execSQL("INSERT INTO item VALUES (58, 'Tomato Ketchup', 'Made with sun-ripened tomatoes for a vibrant flavour', 1.20, 5057373701930, 'Tesco', 52, -8)");
        db.execSQL("INSERT INTO item VALUES (59, 'Tomato Ketchup', 'Made with sun-ripened tomatoes for a vibrant flavour', 1.35, 5057373701930, 'Spar', 53, -3)");
        db.execSQL("INSERT INTO item VALUES (60, 'Tomato Ketchup', 'Made with sun-ripened tomatoes for a vibrant flavour', 1.38, 5057373701930, 'Centra', 53, -3)");

        db.execSQL("INSERT INTO item VALUES (61, 'Coconut Milk', 'Milled from the flesh of fragrant thai coconuts', 1 , 5054269746730, 'Lidl', 52, -8)");
        db.execSQL("INSERT INTO item VALUES (62, 'Coconut Milk', 'Milled from the flesh of fragrant thai coconuts', 1.10, 5054269746730, 'Dunnes', 52, -8)");
        db.execSQL("INSERT INTO item VALUES (63, 'Coconut Milk', 'Milled from the flesh of fragrant thai coconuts', 1.06, 5054269746730, 'Tesco', 52, -8)");
        db.execSQL("INSERT INTO item VALUES (64, 'Coconut Milk', 'Milled from the flesh of fragrant thai coconuts', 1.66, 5054269746730, 'Spar', 53, -3)");
        db.execSQL("INSERT INTO item VALUES (65, 'Coconut Milk', 'Milled from the flesh of fragrant thai coconuts', 1.26, 5054269746730, 'Centra', 53, -3)");

        db.execSQL("INSERT INTO item VALUES (66, 'Flax Seeds', 'Harvested at the peak of the season for a crisp, mildly nutty flavour', 1 , 5057967049592,'Lidl', 52, -8)");
        db.execSQL("INSERT INTO item VALUES (67, 'Flax Seeds', 'Harvested at the peak of the season for a crisp, mildly nutty flavour', 1.17, 5057967049592, 'Dunnes', 52, -8)");
        db.execSQL("INSERT INTO item VALUES (68, 'Flax Seeds', 'Harvested at the peak of the season for a crisp, mildly nutty flavour', 1.30, 5057967049592, 'Tesco', 52, -8)");
        db.execSQL("INSERT INTO item VALUES (69, 'Flax Seeds', 'Harvested at the peak of the season for a crisp, mildly nutty flavour', 1.89, 5057967049592, 'Spar', 53, -3)");
        db.execSQL("INSERT INTO item VALUES (70, 'Flax Seeds', 'Harvested at the peak of the season for a crisp, mildly nutty flavour', 1.86, 5057967049592, 'Centra', 53, -3)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEM);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LIST);
        onCreate(db);
    }


    void addNewList(String Item_name, float Item_price, String Item_store) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //values.put(LIST_ID, ListTable.getList_id());
      try {
          values.put(LIST_ITEM_NAME, Item_name);
          values.put(LIST_ITEM_PRICE, Item_price);
          values.put(LIST_ITEM_STORE, Item_store);
          db.insert(TABLE_LIST, null, values);
          db.close();
      }catch (Exception e){
          Toast.makeText(Ctx, "This item is already in your list", Toast.LENGTH_SHORT).show();
        }
    }


    public boolean deleteList(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_LIST, LIST_ID + "=" + id, null) > 0;
    }
    public List<Item> getAllItemFromBarcode(String barcode) {
        List<Item> ItemList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_ITEM + " WHERE barcode = " + barcode +" ORDER BY price ASC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Item Item = new Item();
                Item.setItem_id(Integer.parseInt(cursor.getString(0)));
                Item.setName(cursor.getString(1));
                Item.setDecription(cursor.getString(2));
                Item.setPrice(Float.parseFloat(cursor.getString(3)));
                Item.setBarcode(cursor.getString(4));
                Item.setStore(cursor.getString(5));
                ItemList.add(Item);
            } while (cursor.moveToNext());
        }
        return ItemList;
    }
    public List<Item> getAllItemFromBarcodewithlocation(String barcode, int lat, int lon) {
        List<Item> ItemList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_ITEM + " WHERE barcode = " + barcode + " AND lat = " + lat +" AND lon = "+ lon +" ORDER BY price ASC" ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Item Item = new Item();
                Item.setItem_id(Integer.parseInt(cursor.getString(0)));
                Item.setName(cursor.getString(1));
                Item.setDecription(cursor.getString(2));
                Item.setPrice(Float.parseFloat(cursor.getString(3)));
                Item.setBarcode(cursor.getString(4));
                Item.setStore(cursor.getString(5));
                ItemList.add(Item);
            } while (cursor.moveToNext());
        }
        return ItemList;
    }


    public List<ListTable> getAllItemFromList() {
        List<ListTable> ListTableList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_LIST;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                ListTable ListTable = new ListTable();
                ListTable.setList_id(cursor.getInt(0));
                ListTable.setItem_name(cursor.getString(1));
                ListTable.setItem_price(Float.parseFloat(cursor.getString(2)));
                ListTable.setItem_store(cursor.getString(3));
                ListTableList.add(ListTable);
            } while (cursor.moveToNext());
        }
        return ListTableList;
    }
}