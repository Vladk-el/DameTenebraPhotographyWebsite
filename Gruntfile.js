module.exports = function (grunt) {

	// Configuration de Grunt
	grunt.initConfig({
		pkg: grunt.file.readJSON('package.json'),
		copy: {
			jquery: {
				expand: true,
				src: './node_modules/jquery/dist/*',
				dest: './lib/jquery/',
				flatten: true
			},
			angular: {
				expand: true,
				src: './node_modules/angular/*',
				dest: './lib/angular/',
				flatten: true
			},
			angular_animate: {
				expand: true,
				src: './node_modules/angular-animate/*',
				dest: './lib/angular-animate/',
				flatten: true
			},
			angular_route: {
				expand: true,
				src: './node_modules/angular-route/*',
				dest: './lib/angular-route/',
				flatten: true
			},
			angular_touch: {
				expand: true,
				src: './node_modules/angular-touch/*',
				dest: './lib/angular-touch/',
				flatten: true
			},
			bootstrap: {
				expand: true,
				cwd: './node_modules/bootstrap/dist/',
				src: '**/*',
				dest: './lib/bootstrap/'
			},
			angularjs_toaster: {
				expand: true,
				src: './node_modules/angularjs-toaster/*',
				dest: './lib/angularjs-toaster/',
				flatten: true
			}
		},
		concat: {
			js_dev: {
				src: [
                    './js/**/*.js',
                    '!./js/client.js'
                ],
				dest: './js/client.js'
			},
			js_prod: {
				src: [
                    './js/**/*.js',
                    '!./js/client.js'
                ],
				dest: './js/client.js'
			},
			css_dev: {
				src: [
                    './css/*.css',
                    '!./css/client.css'
                ],
				dest: './css/client.css'
			},
			css_prod: {
				src: [
                    './css/*.css',
                    '!./css/client.css'
                ],
				dest: './css/client.css'
			}
		},
		ngAnnotate: {
			options: {
				singleQuotes: true
			},
			dist: {
				files: {
					'js/client.js': ['./js/client.js']
				}
			}
		},
		uglify: {
			dist: {
				src: ['./js/client.js'],
				dest: './js/client.js'
			}
		},
		clean: {
			js: ['./js/client.js'],
			css: ['./css/client.css'],
			lib: ['./lib/angular', './lib/angular-animate', './lib/angular-route', './lib/angular-touch', './lib/angularjs-toaster', './lib/bootstrap', './lib/jquery']
		},
		watch: {
			scripts: {
				files: ['./**/*.js', '!./js/client.js'],
				tasks: ['js']
			},
			css: {
				files: ['./**/*.css', '!./css/client.css'],
				tasks: ['css']
			},
			configFiles: {
				files: ['Gruntfile.js'],
				options: {
					reload: true
				}
			}
		}
	});

	// Load npm tasks
	grunt.loadNpmTasks('grunt-contrib-copy');
	grunt.loadNpmTasks('grunt-contrib-concat');
	grunt.loadNpmTasks('grunt-ng-annotate');
	grunt.loadNpmTasks('grunt-contrib-uglify');
	grunt.loadNpmTasks('grunt-contrib-clean');
	grunt.loadNpmTasks('grunt-contrib-watch');

	// Définition des tâches Grunt
	grunt.registerTask('js', ['clean:js', 'concat:js_dev']);
	grunt.registerTask('css', ['clean:css', 'concat:css_dev']);
	grunt.registerTask('move_vendor', ['copy:jquery', 'copy:angular', 'copy:angular_animate', 'copy:angular_route', 'copy:angular_touch', 'copy:bootstrap', 'copy:angularjs_toaster']);

	grunt.registerTask('dev', ['clean:lib', 'move_vendor', 'clean:css', 'concat:css_dev', 'clean:js', 'concat:js_dev']);
	grunt.registerTask('prod', ['clean:lib', 'move_vendor', 'clean:css', 'clean:js', 'concat:css_prod', 'concat:js_prod', 'ngAnnotate:dist', 'uglify:dist']);

}
