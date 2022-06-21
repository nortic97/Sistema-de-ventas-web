<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Categories extends Model
{
    use HasFactory;

    protected $table = 'categories';
    public $timestamps = false;

    protected $fillable = [

    	'id',
    	'category_name',
    	'description'

    ];


    //uno a muchos
    
    public function products_categories(){

    	return $this->hasMany('App\Models\Products_categories');

    }

}
