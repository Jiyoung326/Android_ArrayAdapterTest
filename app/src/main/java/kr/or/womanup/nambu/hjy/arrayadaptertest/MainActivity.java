package kr.or.womanup.nambu.hjy.arrayadaptertest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String[] cats = {"삼색이","마를린","연님","무","래기","야통"};
    ArrayList<String> list;
    ListView listView;
    Button btnDel;
    Button btnAdd;
    Button btnDelMul;
    ArrayAdapter adapter;
    public static final int REQ_NEWMEMBER = 101;
    public static final int REQ_DELMEMBER = 102;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list= new ArrayList<>();
        for(String name:cats){
            list.add(name);
        }

        listView = findViewById(R.id.listview_main);
        btnDel = findViewById(R.id.btn_del);
        btnAdd = findViewById(R.id.btn_add);

        adapter = new ArrayAdapter(MainActivity.this,
                android.R.layout.simple_list_item_checked,list);
        listView.setAdapter(adapter);
//        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = listView.getCheckedItemPosition();
                if(pos!=ListView.INVALID_POSITION){ //포지션이 유효한지 확인
                    list.remove(pos); //유효하면 삭제
                    adapter.notifyDataSetChanged(); //어뎁터에게 데이터 바뀐 사실 알려주기
                    listView.clearChoices();
                }
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getApplicationContext()하면 안드로이드가 어플리케이션 컨택스트 반환해줌
                Intent intent = new Intent(getApplicationContext(), NewMemberActivity.class);

                startActivityForResult(intent,REQ_NEWMEMBER);
            }
        });

        btnDelMul = findViewById(R.id.btn_del_mul);
        btnDelMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //선택한 것만 key : value(true)로 들어감  ex)1:true, 3:true
                SparseBooleanArray checkedItems = listView.getCheckedItemPositions();
                for(int i=list.size()-1;i>-1;i--){
                    if(checkedItems.get(i)){
                        list.remove(i);
                    }
                }
                adapter.notifyDataSetChanged();
                listView.clearChoices();
            }
        });

    }
    //콜백 메소드: 이벤트 결과오면 자동으로 호출되는 함수?
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQ_NEWMEMBER){
            if(resultCode == Activity.RESULT_OK){
                String newName = data.getStringExtra("new_member");
                list.add(newName);
                adapter.notifyDataSetChanged();

            }
        }

    }

}