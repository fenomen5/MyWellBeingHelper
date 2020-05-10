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
import com.barkov.ais.mywellbeinghelper.entity.City;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.util.List;

public class CityAdapter extends BaseAdapter {

    DataBaseHelper dbHelper;
    Context mContext;
    protected  List<City> cities;

    public CityAdapter(Context context) {

        mContext = context;
        dbHelper = OpenHelperManager.getHelper( context, DataBaseHelper.class);

        RuntimeExceptionDao<City, Integer> CitiesDao = dbHelper.getCitiesRuntimeDao();

        cities = CitiesDao.queryForAll();
    }

    @Override
    public int getCount() {

        return cities.size() ;
    }

    @Override
    public Object getItem(int position)
    {
        return cities.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        CityHolder holder;
        View cityView = convertView;

        if (cityView == null) {
            LayoutInflater li = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            cityView = li.inflate(R.layout.city_spinner, parent, false);
            holder = new CityHolder();
            holder.lblCityName = cityView.findViewById(R.id.lblClubTitle);
            cityView.setTag(holder);

        } else
            holder = (CityHolder) cityView.getTag();{
        }

        City city = cities.get(position);
        holder.lblCityName.setText(String.valueOf(city.getName()));
        return cityView;
    }

    class CityHolder {
        private TextView lblCityId;
        private TextView lblCityName;
    }
}
