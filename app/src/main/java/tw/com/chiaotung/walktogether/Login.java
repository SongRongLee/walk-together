package tw.com.chiaotung.walktogether;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;

public class Login extends Activity implements View.OnClickListener,TextWatcher{

    EditText edtAccount,edtPassword;
    Button btLogin,btSignup;
    static SharedPreferences prefs;
    String account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        prefs = getSharedPreferences("LoginInfo",0);
        //initialize
        edtAccount=(EditText)findViewById(R.id.edt_account);
        edtPassword=(EditText)findViewById(R.id.edt_password);
        btLogin=(Button)findViewById(R.id.bt_logIn);
        btSignup=(Button)findViewById(R.id.bt_signUp);

        //set Listeners
        edtAccount.addTextChangedListener(this);
        edtPassword.addTextChangedListener(this);
        btLogin.setOnClickListener(this);
        btSignup.setOnClickListener(this);

        File file = new File("/data/data/tw.com.chiaotung.walktogether/shared_prefs","LoginInfo.xml");
        if(file.exists()){
            ReadValue();
            if(!account.equals("")){
                Intent it=new Intent(this,UserStatus.class);
                startActivity(it);
            }
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_logIn:
                String account=edtAccount.getText().toString();
                String password=edtPassword.getText().toString();
                User user=new User(account,password);

                //check for verification
                verifyUser(user);

                break;
            case R.id.bt_signUp:
                //
                //not yet implemented
                //
                break;
        }

    }
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }
    @Override
    public void afterTextChanged(Editable s) {
        if(!edtAccount.getText().toString().trim().isEmpty()&&!edtPassword.getText().toString().trim().isEmpty()){
            btLogin.setEnabled(true);
        }
        else{
            btLogin.setEnabled(false);
        }
    }
    public void verifyUser(User user){
        ServerRequest request = new ServerRequest(this);
        if(request.fetchUserData(user)==true){
            SharedPreferences.Editor prefEdit = prefs.edit();
            prefEdit.putString("account",user.account);
            prefEdit.commit();
            logInUser(user);
        }
        else{
            showErrorMeassge();
        }
    }
    public void logInUser(User user){
        //
        //set logged in
        //

        //Go to UserStatus page
        Intent it=new Intent(this,UserStatus.class);
        startActivity(it);
    }
    public void showErrorMeassge()
    {
        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setMessage("Wrong user name or password!");
        dialog.setPositiveButton("Ok",null);
        dialog.show();
    }

    public void ReadValue(){
        prefs = getSharedPreferences("LoginInfo",0);
        account = prefs.getString("account","");
    }
}
