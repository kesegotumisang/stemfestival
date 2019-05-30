package org.gdggaborone.stemfestival2019.adapters

import android.content.Context
import android.graphics.Bitmap
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import org.gdggaborone.stemfestival2019.R
import org.gdggaborone.stemfestival2019.models.SpeakerModel
import pl.charmas.android.tagview.TagView
import java.util.*

class SpeakerAdapter(private val mContext: Context, private val modelArrayList: ArrayList<SpeakerModel>) : RecyclerView.Adapter<SpeakerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.speaker_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val speakerModel = modelArrayList[position]

        holder.speakerNameTextView.text = speakerModel.name
        holder.companyAndTitleTextView.text = " ${speakerModel.title}, ${speakerModel.company}"
        holder.bioTextView.text = speakerModel.bio

     //   val tags = arrayOfNulls<TagView.Tag>(speakerModel.tags.size)

        /*for (i in 0 until speakerModel.tags.size) {
            tags[i] = TagView.Tag(speakerModel.tags[i], Color.parseColor("#7e7e7e"))
        }
-----------------------------------
        holder.tagView.setTags(tags, " ")
*/
        Glide.with(mContext)
                .asBitmap()
                .load(speakerModel.photoUrl)
                .listener(object : RequestListener<Bitmap> {
                    override fun onLoadFailed(e: GlideException?, model: Any, target: Target<Bitmap>, isFirstResource: Boolean): Boolean {
                        holder.progressBar.visibility = View.GONE
                        return false
                    }

                    override fun onResourceReady(resource: Bitmap, model: Any, target: Target<Bitmap>, dataSource: DataSource, isFirstResource: Boolean): Boolean {
                        holder.progressBar.visibility = View.GONE
                        return false
                    }
                })
                .into(holder.profilePictureImageView)

        //        String twitterUrl = null;
        //        String gplusUrl = null;
        //        String linkedinUrl = null;

        /*for (i in 0 until (speakerModel.socials!!.size)) {
            when (speakerModel.socials?.get(i)?.icon) {
                "twitter" -> {
                    holder.twitter.setOnClickListener {
                        //                            Toast.makeText(mContext, "twitter", Toast.LENGTH_SHORT).show();
                        mContext.startActivity(Intent(Intent.ACTION_VIEW,
                                Uri.parse(speakerModel.socials?.get(i)!!.link)))
                    }
                    holder.twitter.visibility = View.VISIBLE
                }
                "linkedin" -> {
                    holder.linkedin.setOnClickListener {
                        //                            Toast.makeText(mContext, speakerModel.getSocials().get(0).getLink(), Toast.LENGTH_SHORT).show();
                        mContext.startActivity(Intent(Intent.ACTION_VIEW,
                                Uri.parse(speakerModel.socials?.get(i)!!.link)))
                    }
                    holder.linkedin.visibility = View.VISIBLE
                }
                "gplus" -> {
                    holder.gplus.setOnClickListener {
                        //                            Toast.makeText(mContext, "gplus", Toast.LENGTH_SHORT).show();
                        mContext.startActivity(Intent(Intent.ACTION_VIEW,
                                Uri.parse(speakerModel.socials!![i].link)))
                    }
                    holder.gplus.visibility = View.VISIBLE
                }
            }
        }*/

    }

    override fun getItemCount(): Int {
        return modelArrayList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var progressBar: ProgressBar
        var profilePictureImageView: ImageView
        var twitter: ImageView
        var linkedin: ImageView
        var gplus: ImageView
        var tagView: TagView
        var speakerNameTextView: TextView
        var companyAndTitleTextView: TextView
        var bioTextView: TextView

        init {

            progressBar = itemView.findViewById(R.id.progressBar)

            gplus = itemView.findViewById(R.id.gplus)
            twitter = itemView.findViewById(R.id.twitter)
            linkedin = itemView.findViewById(R.id.linked_in)
            profilePictureImageView = itemView.findViewById(R.id.profilePictureImageView)

            tagView = itemView.findViewById(R.id.tagsTextView)
            speakerNameTextView = itemView.findViewById(R.id.speakerNameTextView)
            companyAndTitleTextView = itemView.findViewById(R.id.companyAndTitle)
            bioTextView = itemView.findViewById(R.id.bioTextView)

        }
    }
}
