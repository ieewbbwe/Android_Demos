package com.webber.demos.database.realm;

import java.util.Random;

import io.realm.DynamicRealm;
import io.realm.DynamicRealmObject;
import io.realm.FieldAttribute;
import io.realm.Realm;
import io.realm.RealmMigration;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;

/**
 * Created by picher on 2018/6/4.
 * Describe：
 */

public class Migration implements RealmMigration {
    @Override
    public void migrate(DynamicRealm dynamicRealm, long oldVersion, long l1) {
        RealmSchema realmSchema =dynamicRealm.getSchema();

        if(oldVersion == 0){
            RealmObjectSchema realmObjectSchema = realmSchema.get(UserRealm.class.getSimpleName());
            realmObjectSchema.addField("id",Integer.class, FieldAttribute.REQUIRED)
                    .transform(new RealmObjectSchema.Function() {
                        @Override
                        public void apply(DynamicRealmObject dynamicRealmObject) {
                            dynamicRealmObject.set("id",new Random().nextInt());
                        }
                    });
            oldVersion += 1;
        }
    }
}
