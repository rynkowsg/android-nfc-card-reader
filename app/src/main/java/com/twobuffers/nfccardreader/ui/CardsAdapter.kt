package com.twobuffers.nfccardreader.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.twobuffers.nfccardreader.R

class CardViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.list_item, parent, false)) {
    private var cardNumberTv: TextView? = null
    private var expiryDateTv: TextView? = null
    private var dateTv: TextView? = null

    init {
        cardNumberTv = itemView.findViewById(R.id.cardNumber)
        expiryDateTv = itemView.findViewById(R.id.expiryDate)
        dateTv = itemView.findViewById(R.id.date)
    }

    fun bind(card: UiCard) {
        cardNumberTv?.text = card.cardNumber
        expiryDateTv?.text = card.expiryDate
        dateTv?.text = card.date
    }
}

class MyAdapter(private val list: List<UiCard>) : RecyclerView.Adapter<CardViewHolder>() {

    private var internalList = mutableListOf(*list.toTypedArray())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CardViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) =
        holder.bind(internalList[position])

    override fun getItemCount() = list.size

    fun set(cards: MutableList<UiCard>) {
        internalList = cards
        this.notifyItemRangeChanged(0, internalList.size);
    }
}
