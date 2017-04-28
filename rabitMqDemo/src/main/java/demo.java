import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.lang.reflect.Field;
import java.util.HashMap;



public class demo {

    public static void main(String[] args) {
        giaovien gv = new giaovien();
        gv.setTen("hello");
        gv.setTuoi("50");
        gv.setBangcap(new String[]{"bang1","bang2"});
        giaosu gs = new giaosu();
        gs.setTuoi("455");
        gs= mapp(gv, gs,giaosu.class);
        //
        System.out.println(gs.getTen()+" "+gs.getTuoi()+" "+gs.getHocvi()+" "+gs.getGioitinh()+" "+gs.getTrinhdo());
        for(int i =0;i<gs.getBangcap().length;i++){
            System.out.println(gs.getBangcap()[i]);
        }
    }

    public static <T, D> D mapp(T t, D d, Class<D> type) {
        HashMap<String, Object> mapT = new HashMap<String, Object>();
        for (Field field : t.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object value = null;
            try {
                value = field.get(t);
                if (value != null) {
                    mapT.put(field.getName(), value);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        //
        HashMap<String, Object> mapD = new HashMap<String, Object>();
        for (Field field : d.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object value = null;
            try {
                value = field.get(d);
                mapD.put(field.getName(), value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        //
        mapT.forEach((x, y) -> {
            mapD.forEach((z, v) -> {
                if (x.equals(z)) {
                    mapD.put(z, y);
                }
            });
        });
        //
        Gson gson = new Gson();
        JsonElement jsonElement = gson.toJsonTree(mapD);
        d= gson.fromJson(jsonElement,type);
        return d;

    }
}
