package notification.android.test.samplerichtexteditor;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import jp.wasabeef.richeditor.RichEditor;
import yuku.ambilwarna.AmbilWarnaDialog;
import yuku.ambilwarna.widget.AmbilWarnaPreference;

public class SampleActivity extends AppCompatActivity {
private RichEditor mEditor;
    private TextView mPreview;
    private static int RESULT_LOAD_IMAGE = 1;
    private int color = 0xffffff00;
    private int bg_color = 0xffffff00;
    //private boolean choice=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
        mEditor = (RichEditor) findViewById(R.id.editor);
        mEditor.setEditorHeight(200);
        mEditor.setEditorFontSize(22);
        mEditor.setEditorFontColor(Color.RED);
        //mEditor.setEditorBackgroundColor(Color.BLUE);
        //mEditor.setBackgroundColor(Color.BLUE);
        //mEditor.setBackgroundResource(R.drawable.bg);
        mEditor.setPadding(10, 10, 10, 10);
        //mEditor.setBackground("https://raw.githubusercontent.com/wasabeef/art/master/chip.jpg");
        mEditor.setPlaceholder("Insert text here...");
        //mEditor.setInputEnabled(false);

        mPreview = (TextView) findViewById(R.id.preview);
        Initialize();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            Log.d( "onActivityResult: ",picturePath);
            //mEditor.loadDataWithBaseURL(picturePath);
           // mEditor.insertImage("http://img3.wikia.nocookie.net/__cb20150103173556/mario/images/c/cf/Legend-of-zelda-spirit-tracks-link-spirit-flute.jpg",
             //       "Image");
            mEditor.insertImage(picturePath,"Image");
            //ImageView imageView = (ImageView) findViewById(R.id.imgView);
            //imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));

        }
    }

    public void Initialize(){
        mEditor.setOnTextChangeListener(new RichEditor.OnTextChangeListener() {
            @Override public void onTextChange(String text) {
                mPreview.setText(text);
            }
        });

        findViewById(R.id.action_undo).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.undo();
            }
        });

        findViewById(R.id.action_redo).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.redo();
            }
        });

        findViewById(R.id.action_bold).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setBold();
            }
        });

        findViewById(R.id.action_italic).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setItalic();
            }
        });

        findViewById(R.id.action_subscript).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setSubscript();
            }
        });

        findViewById(R.id.action_superscript).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setSuperscript();
            }
        });

        findViewById(R.id.action_strikethrough).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setStrikeThrough();
            }
        });

        findViewById(R.id.action_underline).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setUnderline();
            }
        });

        findViewById(R.id.action_heading).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu heading_popup=new PopupMenu(SampleActivity.this,findViewById(R.id.action_heading));
                heading_popup.getMenuInflater().inflate(R.menu.heading_menu,heading_popup.getMenu());
                heading_popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if(item.getItemId()==R.id.action_heading1){mEditor.setHeading(1);}
                        if(item.getItemId()==R.id.action_heading2){mEditor.setHeading(2);}
                        if(item.getItemId()==R.id.action_heading3){mEditor.setHeading(3);}
                        if(item.getItemId()==R.id.action_heading4){mEditor.setHeading(4);}
                        if(item.getItemId()==R.id.action_heading5){mEditor.setHeading(5);}
                        if(item.getItemId()==R.id.action_heading6){mEditor.setHeading(6);}
                        return true;
                    }
                });
                heading_popup.show();
            }
        });
/*
        findViewById(R.id.action_heading1).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setHeading(1);
            }
        });

        findViewById(R.id.action_heading2).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setHeading(2);
            }
        });

        findViewById(R.id.action_heading3).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setHeading(3);
            }
        });

        findViewById(R.id.action_heading4).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setHeading(4);
            }
        });

        findViewById(R.id.action_heading5).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setHeading(5);
            }
        });

        findViewById(R.id.action_heading6).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setHeading(6);
            }
        });
     */
        findViewById(R.id.action_txt_color).setOnClickListener(new View.OnClickListener() {
            private boolean isChanged;

            @Override public void onClick(View v) {
                color_selection(true);
                isChanged = !isChanged;
            }
        });

        findViewById(R.id.action_bg_color).setOnClickListener(new View.OnClickListener() {
            private boolean isChanged;

            @Override public void onClick(View v) {
                color_selection(false);
                isChanged = !isChanged;
            }
        });

        findViewById(R.id.action_indent).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                PopupMenu Indent_popup=new PopupMenu(SampleActivity.this,findViewById(R.id.action_indent));
                Indent_popup.getMenuInflater().inflate(R.menu.indent_popup,Indent_popup.getMenu());
                Indent_popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if(item.getItemId()==R.id.action_indent){mEditor.setIndent();}
                        if(item.getItemId()==R.id.action_outdent){mEditor.setOutdent();}
                        if(item.getItemId()==R.id.action_align_left){ mEditor.setAlignLeft();}
                        if(item.getItemId()==R.id.action_align_center){mEditor.setAlignCenter();}
                        if(item.getItemId()==R.id.action_align_right){mEditor.setAlignRight();}
                        return true;
                    }
                });
                Indent_popup.show();
            }
        });

  /*      findViewById(R.id.action_outdent).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setOutdent();
            }
        });

        findViewById(R.id.action_align_left).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setAlignLeft();
            }
        });

        findViewById(R.id.action_align_center).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setAlignCenter();
            }
        });

        findViewById(R.id.action_align_right).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setAlignRight();
            }
        });
*/
        findViewById(R.id.action_blockquote).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setBlockquote();
            }
        });

        findViewById(R.id.action_insert_bullets).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                PopupMenu List_popoup=new PopupMenu(SampleActivity.this,findViewById(R.id.action_insert_bullets));
                List_popoup.getMenuInflater().inflate(R.menu.oderedlist_menu,List_popoup.getMenu());
                List_popoup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if(item.getItemId()==R.id.action_insert_bullet){mEditor.setBullets();}
                        if(item.getItemId()==R.id.action_insert_numbers){mEditor.setNumbers();}
                        return true;
                    }
                });
                List_popoup.show();
            }
        });

  /*      findViewById(R.id.action_insert_numbers).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setNumbers();
            }
        });
*/
        findViewById(R.id.action_insert_image).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);

                // mEditor.insertImage("http://www.1honeywan.com/dachshund/image/7.21/7.21_3_thumb.JPG",
                //       "dachshund");
            }
        });

        findViewById(R.id.action_insert_link).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                final EditText Href_Link,Href_Title;

                LayoutInflater LI=LayoutInflater.from(SampleActivity.this);
                View PromptsView=LI.inflate(R.layout.href_dailog,null);

                Href_Link=(EditText)PromptsView.findViewById(R.id.Href_Link);
                Href_Title=(EditText)PromptsView.findViewById(R.id.Href_Title);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(SampleActivity.this);

                alertDialogBuilder.setView(PromptsView);
                alertDialogBuilder.setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                mEditor.insertLink(Href_Link.getText().toString(), Href_Title.getText().toString());

                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                               dialog.cancel();
                            }
                        });

                AlertDialog alertDialog=alertDialogBuilder.create();
                alertDialog.show();
                //mEditor.insertLink("https://github.com/wasabeef", "wasabeef");
            }
        });
        findViewById(R.id.action_insert_checkbox).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.insertTodo();
            }
        });
    }

    public void color_selection(final boolean choice){
        AmbilWarnaDialog dialog= new AmbilWarnaDialog(SampleActivity.this, color, false, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {
                Toast.makeText(getApplicationContext(), "cancel", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                Toast.makeText(getApplicationContext(), "ok", Toast.LENGTH_SHORT).show();
                //SampleActivity.this.color = color;
                if(choice==true){ mEditor.setTextColor(color);}
                if(choice==false){mEditor.setTextBackgroundColor(color);}

            }
        });
        dialog.show();

    }


}
