package tw.com.chiaotung.walktogether;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import tw.com.chiaotung.walktogether.view.ModelObject;

/**
 * Created by admin on 2016/3/18.
 */
public class LocalStoreController extends Application {
    public static final String SP_NAME = "userLocalStore";

    static SharedPreferences userLocalStore;

    public LocalStoreController(Context context) {
        userLocalStore = context.getSharedPreferences(SP_NAME, 0);
    }

    public void storeUserID(User user) {
        SharedPreferences.Editor userLocalEditor = userLocalStore.edit();
        userLocalEditor.putInt("mid", user.mid);
        userLocalEditor.commit();
    }

    public void storeAllNameID(User user) {
        SharedPreferences.Editor userLocalEditor = userLocalStore.edit();
        userLocalEditor.putString("name", user.name);
        userLocalEditor.putString("fid_list", user.fid_list);
        userLocalEditor.putString("fname_list", user.fname_list);
        userLocalEditor.putString("oid_list", user.oid_list);
        userLocalEditor.putString("oname_list", user.oname_list);

        userLocalEditor.commit();
    }


    public void storeDevice(ModelObject Object) {
        SharedPreferences.Editor userLocalEditor = userLocalStore.edit();
        userLocalEditor.putString("DeviceName", Object.getName());
        userLocalEditor.putString("DeviceAddress", Object.getAddress());
        userLocalEditor.putString("DeviceRssi", Object.getRssi());
/*
        Gson gson = new Gson();
        String json = gson.toJson(Device);
        String json_flag = gson.toJson(Flag);
        userLocalEditor.putString("Koala", json);
        userLocalEditor.putString("Flag", json_flag);
        */
        userLocalEditor.commit();
    }

    public void storeMessageAmount(int amount)
    {
        SharedPreferences.Editor userLocalEditor = userLocalStore.edit();
        userLocalEditor.putInt("MessageAmount", amount);
        userLocalEditor.commit();
    }

    public void storeStep(int step) {
        SharedPreferences.Editor userLocalEditor = userLocalStore.edit();
        userLocalEditor.putInt("step", step);
        userLocalEditor.commit();
    }

    public void storeSelfLikelist(String likelist)
    {
        SharedPreferences.Editor userLocalEditor = userLocalStore.edit();
        userLocalEditor.putString("Likelist", likelist);
        userLocalEditor.commit();
    }
    public void updateFriendInfo(String fid_list,String fname_list)
    {
        SharedPreferences.Editor userLocalEditor = userLocalStore.edit();
        userLocalEditor.putString("fid_list", fid_list);
        userLocalEditor.putString("fname_list", fname_list);
        userLocalEditor.commit();
    }

    public void updateEveryoneInfo(String fid_list,String fname_list, String oid_list, String oname_list)
    {
        SharedPreferences.Editor userLocalEditor = userLocalStore.edit();
        userLocalEditor.putString("fid_list", fid_list);
        userLocalEditor.putString("fname_list", fname_list);
        userLocalEditor.putString("oid_list", oid_list);
        userLocalEditor.putString("oname_list", oname_list);
        userLocalEditor.commit();
    }
    public void setUserLoggedIn(boolean loggedIn,User user) {
        SharedPreferences.Editor userLocalEditor = userLocalStore.edit();
        userLocalEditor.putBoolean("loggedIn", loggedIn);
        userLocalEditor.putString("account", user.account);
        userLocalEditor.putString("passwd", user.password);
        userLocalEditor.putInt("mid", user.mid);
        userLocalEditor.commit();
    }

    public void clearUserData() {
        SharedPreferences.Editor userLocalEditor = userLocalStore.edit();
        userLocalEditor.clear();
        userLocalEditor.commit();
    }

    public void clearDevice() {
        SharedPreferences.Editor userLocalEditor = userLocalStore.edit();
        userLocalEditor.remove("DeviceName");
        userLocalEditor.remove("DeviceAddress");
        userLocalEditor.remove("DeviceRssi");
        //userLocalEditor.remove("Koala");
        userLocalEditor.apply();
    }

    public User getLogInPreference() {
        String name = userLocalStore.getString("name", "");
        String account = userLocalStore.getString("account", "");
        String password = userLocalStore.getString("passwd", "");
        if(account.equals(""))return null;
        User user = new User(account,password);
        return user;
    }

    public ModelObject getUsedDevice() {
        String DeviceName = userLocalStore.getString("DeviceName", "");
        String DeviceAddress = userLocalStore.getString("DeviceAddress", "");
        String DeviceRssi = userLocalStore.getString("DeviceRssi", "");
        if(DeviceAddress.equals(""))return null;
        ModelObject device = new ModelObject(DeviceName, DeviceAddress, DeviceRssi);
        return device;
    }

    public int getMessageAmount() {
        int amount = userLocalStore.getInt("MessageAmount", 0);
        return amount;
    }

    public int getStep() {
        int step = userLocalStore.getInt("step", 0);
        return step;
    }

    public String getSelfLikelist()
    {
        String likelist = userLocalStore.getString("Likelist", "");
        return likelist;
    }
/*
    public KoalaDevice getUsedDevice_Koala()
    {
        Gson gson = new Gson();
        String json = userLocalStore.getString("Koala", "");
        KoalaDevice koala = gson.fromJson(json, KoalaDevice.class);
        return koala;
    }

    public AtomicBoolean getUsedFlag()
    {
        Gson gson = new Gson();
        String json = userLocalStore.getString("Flag", "");
        AtomicBoolean flag = gson.fromJson(json, AtomicBoolean.class);
        return flag;
    }
    */
}
