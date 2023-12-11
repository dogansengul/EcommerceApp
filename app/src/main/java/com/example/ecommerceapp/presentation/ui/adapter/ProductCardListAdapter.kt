package com.example.ecommerceapp.presentation.ui.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.ecommerceapp.databinding.LayoutProductCardBinding
import com.example.ecommerceapp.business.Product

class ProductCardListAdapter(private val itemClickListener: (Int) -> Unit) : RecyclerView.Adapter<ProductCardListAdapter.ProductCardViewHolder>() {
    private var productList = ArrayList<Product>()

    inner class ProductCardViewHolder(private val binding: LayoutProductCardBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        init {
            binding.root.setOnClickListener(this)
        }
        fun bind(viewState: Product) {
            binding.tvProductTitle.text = viewState.title
            //binding.tvProductDescription.text = viewState.description
            binding.tvPrice.text = viewState.price
            //imageURL loading part with glide...
            Glide.with(binding.root).load(viewState.imageUrl).listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>,
                    isFirstResource: Boolean
                ): Boolean {
                    binding.imageProgressBar.visibility = View.VISIBLE
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable,
                    model: Any,
                    target: Target<Drawable>?,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    binding.imageProgressBar.visibility = View.GONE
                    return false
                }
            }).into(binding.ivProductImage)

        }

        override fun onClick(v: View?) {
            itemClickListener(adapterPosition)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductCardViewHolder {
        return ProductCardViewHolder(LayoutProductCardBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ProductCardViewHolder, position: Int) {
        holder.bind(productList[position])

    }

    fun submitData(newData: ArrayList<Product>) {
        val diffUtil = ProductCardListAdapterDiffUtil(newData, productList)
        val diffResults = DiffUtil.calculateDiff(diffUtil)
        productList.clear()
        productList.addAll(newData)
        diffResults.dispatchUpdatesTo(this)
    }
}