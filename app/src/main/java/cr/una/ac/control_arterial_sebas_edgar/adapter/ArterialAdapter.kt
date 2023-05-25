package cr.una.ac.control_arterial_sebas_edgar.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cr.una.ac.control_arterial_sebas_edgar.R
import cr.una.ac.control_arterial_sebas_edgar.entity.TomarArterial

class ArterialAdapter(var tomasArteriales: ArrayList<TomarArterial>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val VIEW_TYPE_HEADER = 0
    private val VIEW_TYPE_ITEM = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_HEADER) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_header, parent, false)
            HeaderViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
            ViewHolder(view)
        }


    }
    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            VIEW_TYPE_HEADER
        } else {
            VIEW_TYPE_ITEM
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = tomasArteriales[position]

        if (holder is HeaderViewHolder) {
            holder.bind()
        } else if (holder is ViewHolder) {
            val controlArterialItem = item
            holder.bind(controlArterialItem)
        }
    }

    override fun getItemCount(): Int {
        return tomasArteriales.size
    }
    fun updateData(newData: ArrayList<TomarArterial>) {

        tomasArteriales = newData
        if (!newData.isEmpty())
            if(newData[0]._uuid !=null)
                newData.add(0,TomarArterial(null,0,0,0))
        notifyDataSetChanged()
    }

    inner class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(){
            val _uuidTextView = itemView.findViewById<TextView>(R.id._uuid)
            val sistolicaTextView = itemView.findViewById<TextView>(R.id.sistolica)
            val distolicaTextView = itemView.findViewById<TextView>(R.id.diastolica)
            val ritmoTextView = itemView.findViewById<TextView>(R.id.ritmo)
            _uuidTextView.text = "UUID"
            sistolicaTextView.text = "Sistólica"
            distolicaTextView.text = "Diastólica"
            ritmoTextView.text = "Ritmo"
        }

    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val _uuidTextView = itemView.findViewById<TextView>(R.id._uuid)
        val sistolicaTextView = itemView.findViewById<TextView>(R.id.sistolica)
        val distolicaTextView = itemView.findViewById<TextView>(R.id.diastolica)
        val ritmoTextView = itemView.findViewById<TextView>(R.id.ritmo)


        fun bind(tomaArterial: TomarArterial) {
            val sistolica = tomaArterial.sistolica
            val diastolica = tomaArterial.diastolica

            when {
                sistolica < 120 && diastolica < 80 -> itemView.setBackgroundColor(Color.GREEN)
                sistolica in 120..129 && diastolica < 80 -> itemView.setBackgroundColor(Color.YELLOW)
                sistolica in 130..139 && diastolica in 80..89 -> itemView.setBackgroundColor(Color.parseColor("#FFA500")) // Naranja
                sistolica >= 140 || diastolica >= 90 -> itemView.setBackgroundColor(Color.parseColor("#800020")) // Vino
                sistolica > 180 || diastolica > 120 -> itemView.setBackgroundColor(Color.RED)
                else -> itemView.setBackgroundColor(Color.LTGRAY)
            }

            _uuidTextView.text = tomaArterial._uuid.toString()
            sistolicaTextView.text = sistolica.toString()
            distolicaTextView.text = diastolica.toString()
            ritmoTextView.text = tomaArterial.ritmo.toString()
        }
    }

}

