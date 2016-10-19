package com.valarhao.valarnews.module.zhihu.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class ShortCommentActivity extends AppCompatActivity {

    private static final String EXTRA_ID = "EXTRA_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static Intent newIndexIntent(Context context, int id) {
        Intent newIntent = new Intent(context, ShortCommentActivity.class);
        newIntent.putExtra(EXTRA_ID, id);
        return newIntent;
    }
}
