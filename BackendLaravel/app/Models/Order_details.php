<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Order_details extends Model
{
    use HasFactory;

    protected $table = 'order_details';
    public $timestamps = false;

    protected $fillable = [

    	'id',
    	'orders_id',
    	'products_id',
    	'quantity',
    	'total'

    ];


    //relacion muchos a uno.

    public function products(){
    	return $this->belongsTo('App\Models\Products');
    }


    public function orders(){
    	return $this->belongsTo('App\Models\Orders');
    }

}
