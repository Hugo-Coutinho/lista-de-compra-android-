package com.example.administradorlocal.listadecompras.feature.createProducts.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administradorlocal.listadecompras.R;
import com.example.administradorlocal.listadecompras.persistence.entity.Product;
import com.example.administradorlocal.listadecompras.util.imageHelper.ImageManipulate;
import com.example.administradorlocal.listadecompras.util.imageHelper.ImageManipulateImpl;

import java.util.ArrayList;
import java.util.List;

public class ProductListAdapter extends BaseAdapter {

    private List<Product> products;
    private Context ctx;
    private List<Product> productsChecked;
    private TextView tv_showName;
    private ImageManipulate imageManipulate = new ImageManipulateImpl();
    private ImageView iv_show;
    public Product product;

    public ProductListAdapter(List<Product> products, Context ctx) {
        this.products = products;
        this.ctx = ctx;
        this.productsChecked = new ArrayList<>();
        this.product = new Product();
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int position) {
        return products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() { return 500; }

    @Override
    public int getItemViewType(int position) { return position; }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        this.product = products.get(position);
        if (convertView == null) {
            convertView = ((LayoutInflater) ctx.getSystemService(ctx.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.list_view, parent, false);
        }
        this.setupView(convertView);
        this.configureProduct();
        this.configureCheckBox(convertView);

        return convertView;
    }

    private void configureCheckBox(View convertView) {
        final CheckBox cb_item = convertView.findViewById(R.id.cb_item);
        cb_item.setTag(this.product);

        cb_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Product p = (Product) cb_item.getTag();
                if (cb_item.isChecked()) {
                    productsChecked.add(p);
                    Toast.makeText(v.getContext(), p.getName(), Toast.LENGTH_SHORT).show();
                } else {
                    productsChecked.remove(p);
                }
            }
        });
    }

    private void configureProduct() {
        this.iv_show.setImageURI(imageManipulate.getImagebyUri(ctx, product.getImage()));
        this.tv_showName.setText(product.getName());
    }

    private void setupView(View convertView) {
        this.iv_show = convertView.findViewById(R.id.iv_show);
        this.tv_showName = convertView.findViewById(R.id.tv_showName);
    }

    public List<Product> getProductsChecked() {
        return productsChecked;
    }
}

