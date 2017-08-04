package com.example.mfec.myfragment.activity;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.mfec.myfragment.R;
import com.example.mfec.myfragment.fragment.MainFragment;
import com.example.mfec.myfragment.fragment.SecondFragment;
import com.example.mfec.myfragment.util.ScreenUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int screenWidth = ScreenUtil.getInstance().getScreenWidth();
        int screenHeight = ScreenUtil.getInstance().getScreenHeight();
        Toast.makeText(MainActivity.this,
                "width = " + screenWidth +" " + "height = "+screenHeight ,
                Toast.LENGTH_SHORT)
                .show();

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer,MainFragment.newInstance(123),"MainFragment")
                    .commit();

        }

    }
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if(savedInstanceState == null){
            MainFragment fragment =(MainFragment)
                    getSupportFragmentManager().findFragmentByTag("MainFragment");
            fragment.setHelloText("Woooooooooooooo");
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_second_fragment: {
                Fragment fragment = getSupportFragmentManager()
                        .findFragmentById(R.id.contentContainer);

                if(fragment instanceof SecondFragment == false){
                    getSupportFragmentManager().beginTransaction()
                            .setCustomAnimations(
                                    R.anim.from_right,R.anim.to_left,
                                    R.anim.from_left,R.anim.to_right

                            )
//                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .replace(R.id.contentContainer,
                                SecondFragment.newInstance())
                     .addToBackStack(null)
                        .commit();

                }
//                getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.contentContainer,
//                                SecondFragment.newInstance())
////                        .addToBackStack(null)
//                        .commit();
                Toast.makeText(MainActivity.this,
                        "To Second fragment",
                        Toast.LENGTH_SHORT)
                        .show();

                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }





}
