package com.barkov.ais.mywellbeinghelper.dataadapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.barkov.ais.mywellbeinghelper.DataBaseHelper;
import com.barkov.ais.mywellbeinghelper.R;
import com.barkov.ais.mywellbeinghelper.entity.BodyParamType;
import com.barkov.ais.mywellbeinghelper.entity.City;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.util.List;

public class BodyParamAdapter extends BaseAdapter {

    DataBaseHelper dbHelper;
    Context mContext;
    protected  List<BodyParamType> bodyParamTypes;

    public BodyParamAdapter(Context context) {

        mContext = context;
        dbHelper = OpenHelperManager.getHelper( context, DataBaseHelper.class);

        RuntimeExceptionDao<BodyParamType, Integer> bodyParamTypeDao = dbHelper.getBodyParamTypeRuntimeDao();

        bodyParamTypes = bodyParamTypeDao.queryForAll();
    }

    @Override
    public int getCount() {

        return bodyParamTypes.size() ;
    }

    @Override
    public Object getItem(int position)
    {
        return bodyParamTypes.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        BodyParamHolder holder;
        View bodyParamView = convertView;

        if (bodyParamView == null) {
            LayoutInflater li = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            bodyParamView = li.inflate(R.layout.body_params_spinner, parent, false);
            holder = new BodyParamHolder();
            holder.lblParamTypeName = bodyParamView.findViewById(R.id.lblParamsTypes);
            bodyParamView.setTag(holder);

        } else
            holder = (BodyParamHolder) bodyParamView.getTag();{
        }

        BodyParamType bodyParamType = bodyParamTypes.get(position);
        holder.lblParamTypeName.setText(""+ bodyParamType.getName());
        return bodyParamView;
    }

    class BodyParamHolder {
        private TextView lblParamTypeName;
    }
}
