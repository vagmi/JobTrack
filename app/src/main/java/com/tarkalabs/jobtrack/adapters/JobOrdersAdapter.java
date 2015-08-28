package com.tarkalabs.jobtrack.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tarkalabs.jobtrack.R;
import com.tarkalabs.jobtrack.models.JobOrder;
import com.tarkalabs.jobtrack.models.Plant;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.w3c.dom.Text;

import java.util.List;

public class JobOrdersAdapter extends RecyclerView.Adapter<JobOrdersAdapter.ViewHolder> {


    private final List<JobOrder> jobOrders;

    public JobOrdersAdapter(List<JobOrder> jobOrders) {
        this.jobOrders = jobOrders;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View cardView = inflater.inflate(R.layout.card_job_order, parent, false);
        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        JobOrder jobOrder = jobOrders.get(position);
        holder.bindView(jobOrder);
    }

    @Override
    public int getItemCount() {
        return this.jobOrders.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView txtClientName;
        private final TextView txtPartName;
        private final TextView txtStartDate;
        private final TextView txtEndDate;
        private final TextView txtJobId;

        public ViewHolder(View itemView) {
            super(itemView);
            txtClientName = (TextView) itemView.findViewById(R.id.client_name);
            txtPartName = (TextView) itemView.findViewById(R.id.part_name);
            txtStartDate = (TextView) itemView.findViewById(R.id.start_date);
            txtEndDate = (TextView) itemView.findViewById(R.id.end_date);
            txtJobId = (TextView) itemView.findViewById(R.id.job_id);
        }

        public void bindView(JobOrder jobOrder) {
            txtClientName.setText(jobOrder.getClient().getName());
            txtPartName.setText(jobOrder.getDescription());
            DateTimeFormatter fmt = DateTimeFormat.forPattern("dd/MM/yyyy");

            txtStartDate.setText("Starts: " + fmt.print(jobOrder.getStartDate()));
            txtEndDate.setText("Ends: " + fmt.print(jobOrder.getPlannedEndDate()));
            txtJobId.setText(jobOrder.getJobId());
        }
    }
}
