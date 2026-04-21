package com.example.whatsapp;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.appcompat.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.whatsapp.Fragments.ChatFragment;
import com.example.whatsapp.Fragments.CommunityFragment;
import com.example.whatsapp.Fragments.UpdateFragment;
import com.example.whatsapp.Fragments.callFragment;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar mytoolbar = findViewById(R.id.mytoolbar);
        setSupportActionBar(mytoolbar);

        NavigationBarView bottomNavigationView = findViewById(R.id.navButtonNav);
        bottomNavigationView.setOnItemSelectedListener(this);

        // Set default fragment
        if (savedInstanceState == null) {
            loadFragment(new ChatFragment());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.option_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.search);
        if (searchItem != null) {
            searchItem.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
                @Override
                public boolean onMenuItemActionCollapse(@NonNull MenuItem menuItem) {
                    Toast.makeText(MainActivity.this, "Search collapsed", Toast.LENGTH_SHORT).show();
                    return true;
                }

                @Override
                public boolean onMenuItemActionExpand(@NonNull MenuItem menuItem) {
                    Toast.makeText(MainActivity.this, "Search expanded", Toast.LENGTH_SHORT).show();
                    return true;
                }
            });

            SearchView searchView = (SearchView) searchItem.getActionView();
            if (searchView != null) {
                searchView.setQueryHint("Search...");
            }
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.search) {
            Log.d("MENU", "Search clicked");
        } else if (id == R.id.camara) {
            Log.d("MENU", "Camera clicked");
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        int id = item.getItemId();
        
        if (id == R.id.btnchat) {
            fragment = new ChatFragment();
        } else if (id == R.id.btnCall) {
            fragment = new callFragment();
        } else if (id == R.id.btnCommunities) {
            fragment = new CommunityFragment();
        } else if (id == R.id.btnupdate) {
            fragment = new UpdateFragment();
        }

        if (fragment != null) {
            loadFragment(fragment);
            return true;
        }
        return false;
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.linearLayout, fragment)
                .commit();
    }
}
