package com.lwx.mvprx.data.local;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "GIRLS_URLS".
*/
public class GirlsUrlsDao extends AbstractDao<GirlsUrls, Long> {

    public static final String TABLENAME = "GIRLS_URLS";

    /**
     * Properties of entity GirlsUrls.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property ParentId = new Property(1, Long.class, "parentId", false, "PARENT_ID");
        public final static Property Url = new Property(2, String.class, "url", false, "URL");
    }


    public GirlsUrlsDao(DaoConfig config) {
        super(config);
    }
    
    public GirlsUrlsDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"GIRLS_URLS\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"PARENT_ID\" INTEGER," + // 1: parentId
                "\"URL\" TEXT);"); // 2: url
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"GIRLS_URLS\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, GirlsUrls entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Long parentId = entity.getParentId();
        if (parentId != null) {
            stmt.bindLong(2, parentId);
        }
 
        String url = entity.getUrl();
        if (url != null) {
            stmt.bindString(3, url);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, GirlsUrls entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Long parentId = entity.getParentId();
        if (parentId != null) {
            stmt.bindLong(2, parentId);
        }
 
        String url = entity.getUrl();
        if (url != null) {
            stmt.bindString(3, url);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public GirlsUrls readEntity(Cursor cursor, int offset) {
        GirlsUrls entity = new GirlsUrls( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1), // parentId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2) // url
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, GirlsUrls entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setParentId(cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1));
        entity.setUrl(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(GirlsUrls entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(GirlsUrls entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(GirlsUrls entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
