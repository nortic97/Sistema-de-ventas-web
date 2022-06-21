<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Users extends Model
{
    use HasFactory;
    protected $table = 'users';
    public $timestamps = true;

    protected $fillable = [

    	'id',
    	'user_name',
    	'mail',
    	'password',
    	'roles_id',
    	'created_at',
    	'updated_at'

    ];


    protected $hidden = [

    	'password'

    ];

    //relacion muchos a uno.

    public function roles(){

    	return $this->belongsTo('App\Models\Roles');

    }


    //uno a muchos 
    public function persons(){

    	return $this->hasMany('App\Models\Persons');

    }

}
