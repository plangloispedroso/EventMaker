package com.parm.eventmaker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MenuActivity extends AppCompatActivity {


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.mymenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.MENU_ABOUT:
                showAbout();
                return true;
            case R.id.MENU_SEARCH:
                showSearch();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showAbout(){
        Intent abt = new Intent();

        startActivity(abt);
    }

    private void showSearch(){
        Intent srch = new Intent();

        startActivity(srch);
    }

}
