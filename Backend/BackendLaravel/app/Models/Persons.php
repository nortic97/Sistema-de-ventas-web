<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Persons extends Model
{
    use HasFactory;


    protected $table = 'persons';
    public $timestamps = false;

    protected $fillable = [

    	'id',
    	'users_id',
    	'name',
    	'surname',
    	'DNI',
    	'image',
    	'address',
    	'phone'

    ];


    //muchos a uno
    public function users(){
    	return $this->belongsTo('App\Models\Users');
    }
}
