package com.translate.adapterFile;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.translate.R;
import com.translate.model.Vocabulary;

import java.io.IOException;
import java.util.List;

public class VocabularyAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Vocabulary> vocabularyList;

    public VocabularyAdapter(Context context, int layout, List<Vocabulary> vocabularyList) {
        this.context = context;
        this.layout = layout;
        this.vocabularyList = vocabularyList;
    }

    @Override
    public int getCount() {
        return vocabularyList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder {
        TextView textViewword;
        TextView textViewtype;
        TextView textViewspell;
        ImageView imageViewsound;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(layout, null);
            viewHolder.textViewword = convertView.findViewById(R.id.textViewword);
            viewHolder.textViewtype = convertView.findViewById(R.id.textViewtype);
            viewHolder.textViewspell = convertView.findViewById(R.id.textViewspell);
            viewHolder.imageViewsound = convertView.findViewById(R.id.imageViewsound);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Vocabulary vocabulary=vocabularyList.get(position);
        viewHolder.textViewword.setText(vocabulary.getWord()+" : "+vocabulary.getMean());
        viewHolder.textViewtype.setText("type: "+vocabulary.getType());
        viewHolder.textViewspell.setText(vocabulary.getSpelling());
        final String url=vocabulary.getAudioFile();
        viewHolder.imageViewsound.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Toast.makeText(context, url, Toast.LENGTH_SHORT).show();
                MediaPlayer mediaPlayer=new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                try {
                    mediaPlayer.setDataSource(url);
                    mediaPlayer.prepareAsync();
                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            mp.start();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        return convertView;
    }
}
