package com.example.sql_database.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sql_database.EditActivity;
import com.example.sql_database.MainActivity;
import com.example.sql_database.R;
import com.example.sql_database.db.MyConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * Адаптер recycler view
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder> {
    //принимает контекст с вью
    private Context context;
    private List<ListItem> mainArray;
    //конструктор который создается вместе с recycler view
    public MainAdapter(Context context) {
        this.context = context;
        mainArray = new ArrayList<>();
    }

    //метод который рисует на экране разметку
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //создаем вью и передаем новый лайоут для разметки
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_layout, parent, false);
        return new MyViewHolder(view, context, mainArray);
    }
    //заполняет элементами
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.setData(mainArray.get(position).getTitle());
    }
    //отвечает за количество запусков методов которые выше
    @Override
    public int getItemCount() {
        return mainArray.size();
    }

    //вложенный класс для нахождения элемента который будет отрисован
    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvTitle;
        private Context context;
        private List<ListItem> mainArray;

        public MyViewHolder(@NonNull View itemView, Context context, List<ListItem> mainArray) {
            super(itemView);
            this.context = context;
            this.mainArray = mainArray;
            tvTitle = itemView.findViewById(R.id.tvTitle);
            itemView.setOnClickListener(this);
        }
        //берем с List наш текст
        public void setData(String title){
            tvTitle.setText(title);
        }
        //слушатель нажатий каждого добавленного элемента в ресайклер вью
        @Override
        public void onClick(View v) {
            //запуск активити
            Intent i = new Intent(context, EditActivity.class);
            i.putExtra(MyConstants.LIST_ITEM_INTENT,mainArray.get(getAdapterPosition()));
            i.putExtra(MyConstants.EDIT_STATE, false); //если фолс то для просмотра
            context.startActivity(i);
        }
    }
    public void updateAdapter(List<ListItem> newList){
        mainArray.clear();
        mainArray.addAll(newList);
        notifyDataSetChanged(); //передать в адаптер
    }
}
