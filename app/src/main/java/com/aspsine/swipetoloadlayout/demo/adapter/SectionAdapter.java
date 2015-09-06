package com.aspsine.swipetoloadlayout.demo.adapter;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.demo.R;
import com.aspsine.swipetoloadlayout.demo.model.Hero;
import com.aspsine.swipetoloadlayout.demo.model.Section;
import com.squareup.picasso.CircleTransformation;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aspsine on 15/9/4.
 */
public class SectionAdapter extends BaseGroupAdapter<Section, Hero> {


    List<Section> mSections;

    public SectionAdapter() {
        mSections = new ArrayList<>();
    }

    public void setList(List<Section> sections) {
        mSections.clear();
        append(sections);
    }

    public void append(List<Section> sections) {
        mSections.addAll(sections);
        notifyDataSetChanged();
    }

    @Override
    protected int getParentViewType(int groupPosition) {
        return -1;
    }

    @Override
    public int getGroupCount() {
        return mSections.size();
    }

    @Override
    protected Section getGroup(int groupPosition) {
        return mSections.get(groupPosition);
    }

    @Override
    protected View getGroupView(int groupPosition, View convertView, ViewGroup parent) {
        HeaderViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_header, parent, false);
            holder = new HeaderViewHolder();
            holder.tvHeader = (TextView) convertView.findViewById(R.id.tvHeader);
            convertView.setTag(holder);
        } else {
            holder = (HeaderViewHolder) convertView.getTag();
        }
        String header = getGroup(groupPosition).getName();
        holder.tvHeader.setText(header);
        return convertView;
    }

    @Override
    protected int getChildViewType(int groupPosition, int childPositionInGroup) {
        return 1;
    }

    @Override
    public int getChildCount(int groupPosition) {
        return getGroup(groupPosition).getHeroes().size();
    }

    @Override
    protected Hero getChild(int groupPosition, int childPosition) {
        return getGroup(groupPosition).getHeroes().get(childPosition);
    }

    @Override
    protected View getChildView(int groupPosition, int childPosition, View convertView, ViewGroup parent) {
        ChildViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hero, parent, false);
            holder = new ChildViewHolder();
            holder.tvName = (TextView) convertView.findViewById(R.id.tvName);
            holder.ivAvatar = (ImageView) convertView.findViewById(R.id.ivAvatar);
            convertView.setTag(holder);
        } else {
            holder = (ChildViewHolder) convertView.getTag();
        }
        Hero hero = getChild(groupPosition, childPosition);
        holder.tvName.setText(hero.getName());
        Resources resources = parent.getResources();
        int size = resources.getDimensionPixelOffset(R.dimen.hero_avatar_size);
        int width = resources.getDimensionPixelOffset(R.dimen.hero_avatar_border);
        Picasso.with(parent.getContext())
                .load(hero.getAvatar())
                .resize(size, size)
                .transform(new CircleTransformation(width))
                .into(holder.ivAvatar);
        return convertView;
    }

    public class HeaderViewHolder {
        TextView tvHeader;

    }

    public class ChildViewHolder {
        ImageView ivAvatar;
        TextView tvName;
    }
}
