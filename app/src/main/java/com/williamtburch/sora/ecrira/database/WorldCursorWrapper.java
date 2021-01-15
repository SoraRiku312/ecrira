package com.williamtburch.sora.ecrira.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.williamtburch.sora.ecrira.World;

import java.util.UUID;

public class WorldCursorWrapper extends CursorWrapper {
    public WorldCursorWrapper(Cursor cursor){
        super(cursor);
    }

    public World getWorld(){
        String uuidString = getString(getColumnIndex(WorldDbSchema.WorldTable.Cols.UUID));
        String name = getString(getColumnIndex(WorldDbSchema.WorldTable.Cols.WORLDNAME));
        String worldType = getString(getColumnIndex(WorldDbSchema.WorldTable.Cols.WORLDTYPE));


        World world = new World(UUID.fromString(uuidString));
        world.setWorldName(name);
        world.setWorldType(Integer.valueOf(worldType));
        return world;

    }
}
