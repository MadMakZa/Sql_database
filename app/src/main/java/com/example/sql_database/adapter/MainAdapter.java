package com.example.sql_database.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sql_database.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Адаптер recycler view
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder> {
    //принимает контекст с вью
    private Context context;
    private List<String> mainArray;
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
        return new MyViewHolder(view);
    }
    //заполняет элементами
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.setData(mainArray.get(position));
    }
    //отвечает за количество запусков методов которые выше
    @Override
    public int getItemCount() {
        return mainArray.size();
    }

    //вложенный класс для нахождения элемента который будет отрисован
    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
        }
        //берем с List наш текст
        public void setData(String title){
            tvTitle.setText(title);
        }
    }
    public void updateAdapter(List<String> newList){
        mainArray.clear();
        mainArray.addAll(newList);
        notifyDataSetChanged(); //передать в адаптер
    }
}
