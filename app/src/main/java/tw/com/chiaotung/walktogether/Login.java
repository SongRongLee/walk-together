package tw.com.chiaotung.walktogether;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends Activity implements View.OnClickListener,TextWatcher{

    EditText edtAccount,edtPassword;
    Button btLogin,btSignup;
    LocalStoreController storeController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //initialize
        edtAccount = (EditText) findViewById(R.id.edt_account);
        edtPassword = (EditText) findViewById(R.id.edt_password);
        btLogin = (Button) findViewById(R.id.bt_logIn);
        btSignup = (Button) findViewById(R.id.bt_signUp);
        storeController=new LocalStoreController(this);

        //check account logged in
        //File file = new File("/data/data/tw.com.chiaotung.walktogether/shared_prefs", "LoginInfo.xml");
        User returnedUser = storeController.getLogInPreference();
        if (returnedUser!=null) {
                verifyUser(returnedUser);
        }

        //set Listeners
        edtAccount.addTextChangedListener(this);
        edtPassword.addTextChangedListener(this);
        btLogin.setOnClickListener(this);
        btSignup.setOnClickListener(this);
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

                //Sign up
                Intent it=new Intent(this,SignUp.class);
                startActivity(it);
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
    public void verifyUser(User user) {
        ServerRequest request = new ServerRequest(this);

        request.logInCheck(user, new CallBack() {
            @Override
            public void done(CallBackContent content) {
                if (content != null) {
                    logInUser(content.user);
                } else {
                    showErrorMeassge();
                }
            }
        });
    }
    public void logInUser(User user){
        //set logged in
        storeController.setUserLoggedIn(true,user);

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
}