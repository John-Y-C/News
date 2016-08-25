package com.example.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.apptest.HomeActivity;
import com.example.apptest.R;
import com.example.bean.Categories;
import com.example.pages.PageNews;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/8/22/022.
 */
public class LeftFragment extends Fragment {

    @BindView(R.id.lv_leftfragment_items)
    ListView lv_leftfragment_items;
    List<Categories.DataBean> dataBeanList;

    private Categories categories;
    private MyLeftAdapter myLeftAdapter;

    private int currentPosition;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View fragment_left = View.inflate(getActivity(), R.layout.fragment_left, null);

        final HomeActivity homeActivity = (HomeActivity) getActivity();

        dataBeanList = new ArrayList<>();

        ButterKnife.bind(this, fragment_left);

        lv_leftfragment_items.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                currentPosition = position;
                //每点击一次就刷新一次页面，防止都变红
                myLeftAdapter.notifyDataSetChanged();

                //点击更换内容
                ContentFragment contentFragment = homeActivity.getContentFragment();
                PageNews pageNews = contentFragment.getPageNews();
                Log.d("LeftFragment", "position:" + position);
                if (pageNews!=null)
                {
                    pageNews.changeMenuPage(position);
                }

                //更改完之后让侧栏自动收回
                homeActivity.toggle();
            }
        });

        return fragment_left;//super.onCreateView(inflater, container, savedInstanceState);
    }

    class MyLeftAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            Log.d("LeftFragment", "categories.data.size():" + categories.data.size());
            return  categories.data.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            Categories.DataBean dataBean = categories.data.get(position);

            View item_leftmenu = View.inflate(getActivity(), R.layout.item_leftmenu, null);
            ViewHolder viewHolder = new ViewHolder(item_leftmenu);
            viewHolder.tv_itemleft_items.setText(dataBean.title);
            //Log.d("MyLeftAdapter", dataBean.title);

            if (currentPosition != position) {
                viewHolder.tv_itemleft_items.setEnabled(false);
            } else {
                viewHolder.tv_itemleft_items.setEnabled(true);
            }

            //注意要返回view
            return viewHolder.tv_itemleft_items;
        }

        class ViewHolder {
            @BindView(R.id.tv_itemleft_items)
            TextView tv_itemleft_items;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }

    public void setDataBean(Categories categories) {
        this.categories = categories;
        Log.d("LeftFragment", "-----" + categories.toString());
        myLeftAdapter = new MyLeftAdapter();

        lv_leftfragment_items.setAdapter(myLeftAdapter);
    }
}
