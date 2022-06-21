<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Roles extends Model
{
    use HasFactory;


    protected $table = 'roles';
    public $timestamps = false;

    protected $fillable = [

    	'id',
    	'role_name'

    ];


    /**
	* Uno a muchos, un rol pertenece a muchos usuarios.
    */

    public function users(){

    	return $this->hasMany('App\Models\Users');

    }

}
