package br.com.brunoccbertolini.ecommerce_whitelabel.ui.products

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.brunoccbertolini.ecommerce_whitelabel.databinding.ItemProductBinding
import br.com.brunoccbertolini.ecommerce_whitelabel.domain.model.Product
import br.com.brunoccbertolini.ecommerce_whitelabel.util.toCurrency
import com.bumptech.glide.Glide

class ProductsAdapter() : RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<Product>(){
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.description == newItem.description
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        return ProductsViewHolder(
            ItemProductBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        val product = differ.currentList[position]
        holder.bind(product)
    }

    override fun getItemCount() = differ.currentList.size

    inner class ProductsViewHolder(private val item: ItemProductBinding) :
        RecyclerView.ViewHolder(item.root) {

        fun bind(product: Product) {
            item.run {
                Glide.with(itemView)
                    .load(product.imageUrl)
                    .fitCenter()
                    .into(imageProduct)

                textDescription.text = product.description
                textPrice.text = product.price.toCurrency()
            }
        }
    }

}
