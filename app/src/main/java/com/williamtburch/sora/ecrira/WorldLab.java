package com.williamtburch.sora.ecrira;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.williamtburch.sora.ecrira.database.WorldBaseHelper;
import com.williamtburch.sora.ecrira.database.WorldCursorWrapper;
import com.williamtburch.sora.ecrira.database.WorldDbSchema;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class WorldLab {

    private static WorldLab sWorldLab;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static WorldLab get(Context context){
        if(sWorldLab == null){
            sWorldLab = new WorldLab(context);
        }
        return sWorldLab;
    }

    private WorldLab(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new WorldBaseHelper(mContext).getWritableDatabase();
    }

    public void addWorld(World w){
        ContentValues values = getContentValues(w);

        mDatabase.insert(WorldDbSchema.WorldTable.NAME, null, values);
    }

    public List<World> getWorlds(){
        List<World>worlds = new ArrayList<>();

        WorldCursorWrapper cursor = queryWorlds(null, null);

        try{
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                worlds.add(cursor.getWorld());
                cursor.moveToNext();
            }
        } finally{
            cursor.close();
        }
        return worlds;
    }

    public World getWorld(UUID id){
        WorldCursorWrapper cursor = queryWorlds(
                WorldDbSchema.WorldTable.Cols.UUID + " = ?",
                new String[] {id.toString()}
        );
        try{
            if(cursor.getCount() == 0){
                return null;
            }
            cursor.moveToFirst();
            return cursor.getWorld();
        } finally{
            cursor.close();
        }
    }

    public void updateWorld(World world){
        String uuidString = world.getID().toString();
        ContentValues values = getContentValues(world);

        mDatabase.update(WorldDbSchema.WorldTable.NAME, values,
                WorldDbSchema.WorldTable.Cols.UUID + " = ?",
                new String[]{uuidString});
    }

    private WorldCursorWrapper queryWorlds(String whereClause, String[] whereArgs){
        Cursor cursor = mDatabase.query(
                WorldDbSchema.WorldTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
        return new WorldCursorWrapper(cursor);
    }

    private static ContentValues getContentValues(World world){
        ContentValues values = new ContentValues();
        values.put(WorldDbSchema.WorldTable.Cols.UUID, world.getID().toString());
        values.put(WorldDbSchema.WorldTable.Cols.WORLDNAME, world.getWorldName());
        values.put(WorldDbSchema.WorldTable.Cols.WORLDTYPE, world.getWorldType());

        return values;
    }
}
