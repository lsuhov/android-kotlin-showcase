package com.test.showcase.masterdetailkotlintechnology.articleList

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.test.showcase.data.model.ArticlePreviewModel
import com.test.showcase.masterdetail_kotlintechnology.R
import com.test.showcase.masterdetail_kotlintechnology.databinding.ArticleListItemBinding


class ArticlesListAdapter(private var list: List<ArticlePreviewModel>, val callback: IArticleListItemClicked) : RecyclerView.Adapter<ArticlesListAdapter.ArticleItemViewHolder>() {

    class ArticleItemViewHolder(private val binding: ArticleListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindViewModel(viewModel: ArticleListItemViewModel, callback: IArticleListItemClicked) {
            binding.viewModel = viewModel

            binding.cardView.setOnClickListener { callback.onArticleClicked(viewModel.url) }

            //setSpannableForAggregateInfo()
        }

        // Attempt to solve the author inline with calendar image and the date of posting
//        private fun setSpannableForAggregateInfo() {
//            val ssb = SpannableStringBuilder()
//            val flag = Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
//
//            ssb.append(binding.viewModel?.author)
//
//            var start: Int
//            var end: Int = ssb.length
//            // Inline image
//            ssb.append('\n')
//            start = end + 1
//            ssb.append('\uFFFC') // Unicode replacement character
//            end = ssb.length
//            ssb.setSpan(ImageSpan(binding.root.context, R.drawable.ic_calendar), start, end, flag)
//
//            ssb.append(binding.viewModel?.date)
//
//            binding.authorTextView.text = ssb
//        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ArticleListItemBinding>(
                layoutInflater, R.layout.article_list_item, parent, false)

        return ArticleItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleItemViewHolder, position: Int) {
        val model = list[position]
        val itemViewModel = ArticleListItemViewModel(model)

        holder.bindViewModel(itemViewModel, callback)
    }

    override fun getItemCount(): Int = list.size

    fun replaceData(newList: List<ArticlePreviewModel>) {
        list = newList

        //in more complex scenarios this is not desired. DiffUtil will be needed
        notifyDataSetChanged()
    }

}