package ru.ohapegor.travelservice.util;


import ru.ohapegor.travelservice.entities.AbstractBaseEntity;

public class TourUtil {

    public static boolean isNew(AbstractBaseEntity entity){
        return entity.getId() == null;
    }

}
