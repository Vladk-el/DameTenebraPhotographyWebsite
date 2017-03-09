module.exports = function (grunt) {

	// Configuration de Grunt
	grunt.initConfig({
		pkg: grunt.file.readJSON('package.json'),
		copy: {
			jquery: {
				expand: true,
				src: './node_modules/jquery/dist/*',
				dest: './lib/vendor/jquery/',
				flatten: true
			},
			angular: {
				expand: true,
				src: './node_modules/angular/*',
				dest: './lib/vendor/angular/',
				flatten: true
			},
			angular_animate: {
				expand: true,
				src: './node_modules/angular-animate/*',
				dest: './lib/vendor/angular-animate/',
				flatten: true
			},
			angular_sanitize: {
				expand: true,
				src: './node_modules/angular-sanitize/*',
				dest: './lib/vendor/angular-sanitize/',
				flatten: true
			},
			angular_route: {
				expand: true,
				src: './node_modules/angular-route/*',
				dest: './lib/vendor/angular-route/',
				flatten: true
			},
			angular_touch: {
				expand: true,
				src: './node_modules/angular-touch/*',
				dest: './lib/vendor/angular-touch/',
				flatten: true
			},
			angular_ui_bootstrap: {
				expand: true,
				src: './node_modules/angular-ui-bootstrap/dist/*',
				dest: './lib/vendor/angular-ui-bootstrap/',
				flatten: true
			},
			angular_ui_bootstrap_tpl: {
				expand: true,
				cwd: './node_modules/angular-ui-bootstrap/template',
				src: '**/*',
				dest: './lib/vendor/angular-ui-bootstrap/tpl'
			},
			tether: {
				expand: true,
				cwd: './node_modules/tether/dist/',
				src: '**/*',
				dest: './lib/vendor/tether/'
			},
			bootstrap: {
				expand: true,
				cwd: './node_modules/bootstrap/dist/',
				src: '**/*',
				dest: './lib/vendor/bootstrap/'
			},
			angularjs_toaster: {
				expand: true,
				src: './node_modules/angularjs-toaster/*',
				dest: './lib/vendor/angularjs-toaster/',
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
			lib: ['./lib/vendor']
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
	grunt.registerTask('move_vendor', ['copy:jquery', 'copy:angular', 'copy:angular_animate', 'copy:angular_sanitize', 'copy:angular_route', 'copy:angular_touch', 'copy:angular_ui_bootstrap', 'copy:angular_ui_bootstrap_tpl', 'copy:tether', 'copy:bootstrap', 'copy:angularjs_toaster']);

	grunt.registerTask('dev', ['clean:lib', 'move_vendor', 'clean:css', 'concat:css_dev', 'clean:js', 'concat:js_dev']);
	grunt.registerTask('prod', ['clean:lib', 'move_vendor', 'clean:css', 'clean:js', 'concat:css_prod', 'concat:js_prod', 'ngAnnotate:dist', 'uglify:dist']);

}
