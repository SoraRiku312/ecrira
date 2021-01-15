package com.williamtburch.sora.ecrira.database;

public class WorldDbSchema{

    public static final class WorldTable{
        public static final String NAME = "worlds";

        public static final class Cols{
            public static final String UUID = "uuid";
            public static final String WORLDNAME = "name";
            public static final String WORLDTYPE = "type";
        }
    }

}