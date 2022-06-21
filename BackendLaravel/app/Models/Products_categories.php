<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Products_categories extends Model
{
    use HasFactory;


    protected $table = 'products_categories';
    public $timestamps = false;

    protected $fillable = [

    	'id',
    	'categories_id',
    	'products_id'

    ];

	//muchos a uno.
    public function categories(){
    	return $this->belongsTo('App\Models\Categories');
    }


    public function products(){
        return $this->belongsTo('App\Models\Products');
    }

}
