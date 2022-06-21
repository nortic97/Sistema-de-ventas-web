<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Products extends Model
{
    use HasFactory;

    protected $table = 'products';
    public $timestamps = false;

    protected $fillable = [

    	'id',
    	'name',
    	'code',
    	'price',
    	'description',
    	'stock',
    	'image'


    ];


    //uno a muchos.

    public function products_categories(){

    	return $this->hasMany('App\Models\Products_categories');

    }

    public function order_details(){
    	return $this->hasMany('App\Models\Prder_details');
    }
}
