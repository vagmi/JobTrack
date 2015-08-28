package com.tarkalabs.jobtrack.adapters;


import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tarkalabs.jobtrack.R;
import com.tarkalabs.jobtrack.models.Shift;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.List;

public class ShiftsAdapter extends RecyclerView.Adapter<ShiftsAdapter.ViewHolder> {
    private final List<Shift> shifts;

    public ShiftsAdapter(List<Shift> shifts) {
        this.shifts = shifts;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View shiftView = inflater.inflate(R.layout.card_shift, parent, false);
        return new ViewHolder(shiftView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Shift shift = shifts.get(position);
        holder.bindShift(shift);
    }

    @Override
    public int getItemCount() {
        return shifts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView txtShiftNumber;
        private final RecyclerView checkinsView;
        private final CheckInAdapter checkInAdapter;


        public ViewHolder(View itemView) {
            super(itemView);
            checkInAdapter = new CheckInAdapter();
            txtShiftNumber = (TextView) itemView.findViewById(R.id.shift_number);
            checkinsView = (RecyclerView) itemView.findViewById(R.id.checkins);
            GridLayoutManager gridManager = new GridLayoutManager(itemView.getContext(),2);
            checkinsView.setAdapter(checkInAdapter);
            checkinsView.setLayoutManager(gridManager);

        }
        public void bindShift(Shift shift) {
            DateTime shiftDate = shift.getShiftDate();
            DateTimeFormatter fmt = DateTimeFormat.forPattern("dd/MMMM/yyyy");
            txtShiftNumber.setText(fmt.print(shiftDate) + " #" + shift.getShiftNumber());
            checkInAdapter.getCheckIns().clear();
            checkInAdapter.getCheckIns().addAll(shift.getCheckIns());
            checkInAdapter.notifyDataSetChanged();
        }
    }
}
