package com.tarkalabs.jobtrack.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tarkalabs.jobtrack.R;
import com.tarkalabs.jobtrack.models.CheckIn;
import com.tarkalabs.jobtrack.ui.views.RoundedImageView;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;


public class CheckInAdapter extends RecyclerView.Adapter<CheckInAdapter.ViewHolder> {
    List<CheckIn> checkIns;

    public CheckInAdapter() {
        checkIns = new ArrayList<CheckIn>();

    }

    public List<CheckIn> getCheckIns() {
        return checkIns;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_checkin, parent, false);
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindCheckIn(checkIns.get(position));
    }

    @Override
    public int getItemCount() {
        return checkIns.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final RoundedImageView imgAvatar;
        private final TextView txtEmployeeName;
        private final TextView txtCheckinDate;

        public ViewHolder(View itemView) {
            super(itemView);
            imgAvatar = (RoundedImageView) itemView.findViewById(R.id.avatar);
            txtEmployeeName = (TextView) itemView.findViewById(R.id.employee_name);
            txtCheckinDate = (TextView) itemView.findViewById(R.id.checkin_date);
        }

        public void bindCheckIn(CheckIn checkIn) {
            imgAvatar.setImageResource(checkIn.getEmployee().getDrawable());
            txtEmployeeName.setText(checkIn.getEmployee().getName());
            if(checkIn.getCheckInDate()!=null) {
                DateTimeFormatter fmt = DateTimeFormat.forPattern("dd/MM/yyyy hh:mm");
                txtCheckinDate.setText(fmt.print(checkIn.getCheckInDate()));
            } else {
                txtCheckinDate.setText("");
            }
        }

    }
}
