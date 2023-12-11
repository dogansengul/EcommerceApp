package com.example.ecommerceapp.presentation.ui.view

import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.ecommerceapp.databinding.FragmentProductDetailsBinding
import com.example.ecommerceapp.business.Product
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailsFragment : Fragment() {

    private var _binding: FragmentProductDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentProductDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var product: Product? = null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            product = arguments?.getParcelable("product", Product::class.java)
        } else {
            product = arguments?.getParcelable<Product>("product")
        }
        setUpLayout(product?.title, product?.imageUrl, product?.description, product?.price)
    }

    private fun setUpLayout(title: String?, image: String?, description: String?, price: String?) {
        if (title.isNullOrEmpty() && image.isNullOrEmpty() && description.isNullOrEmpty()
            && price.isNullOrEmpty()) {
            binding.ivProductImage.visibility = View.GONE
            binding.tvProductName.visibility = View.GONE
            binding.tvProductDescription.visibility = View.GONE
            binding.tvProductPrice.visibility = View.GONE
            binding.textView.visibility = View.VISIBLE
        } else {
            binding.ivProductImage.visibility = View.VISIBLE
            binding.tvProductName.visibility = View.VISIBLE
            binding.tvProductDescription.visibility = View.VISIBLE
            binding.tvProductPrice.visibility = View.VISIBLE
            binding.textView.visibility = View.GONE
            Glide.with(binding.root).load(image).listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable,
                    model: Any,
                    target: Target<Drawable>?,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

            }).into(binding.ivProductImage)
            binding.tvProductName.text = title
            binding.tvProductDescription.text = description
            binding.tvProductPrice.text = price
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}