<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class orders extends Model
{
    use HasFactory;


    protected $table = 'orders';

    public $timestamps = false;

    protected $fillable = [

    	'id',
    	'persons_id',
    	'ammount',
    	'order_date',
    	'order_status'

    ];

	//relacion uno a muchos.
    public function order_details(){
    	return $this->hasMany('App\Models\Order_details');
    }

    //relaciones muchos a uno.
    public function persons(){
    	return $this->belongsTo('App\Models\Persons');
    }

}
