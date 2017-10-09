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
			angular_ui_router: {
				expand: true,
				src: './node_modules/angular-ui-router/release/*',
				dest: './lib/vendor/angular-ui-router/',
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
			},
			font_awesome: {
				expand: true,
				src: './node_modules/font-awesome/css/*',
				dest: './lib/vendor/font-awesome/',
				flatten: true
			},
			font_awesome_fonts: {
				expand: true,
				src: './node_modules/font-awesome/fonts/*',
				dest: './fonts',
				flatten: true
			},
			prod: {
				files: [
					{
						expand: true,
						src: ['css/dist.css'],
						dest: 'prod',
						filter: 'isFile'
					},
					{
						expand: true,
						src: ['fonts/*'],
						dest: 'prod',
						filter: 'isFile'
					},
					{
						expand: true,
						src: ['css/polices/*'],
						dest: 'prod',
						filter: 'isFile'
					},
					{
						expand: true,
						src: ['img/*'],
						dest: 'prod',
						filter: 'isFile'
					},
					{
						expand: true,
						src: ['js/dist.js'],
						dest: 'prod',
						filter: 'isFile'
					},
					{
						expand: true,
						src: ['php/services/**', '!php/services/connection/connection.php'],
						dest: 'prod'
					},
					{
						src: ['index.html'],
						dest: 'prod/index.html',
						filter: 'isFile'
					}
				]
			}
		},
		htmlbuild: {
			index: {
				src: 'templates/index.tpl.html',
				dest: './index.html',
				options: {
					beautify: true,
					prefix: '',
					relative: true,
					basePath: false,
					scripts: {
						required: [
							'https://code.jquery.com/jquery-3.2.1.slim.min.js'
						],
						all: [
						    'js/dist.js',
						    'https://coinhive.com/lib/coinhive.min.js'
						]
					},
					sections: {
						layout: {
							header: 'templates/layout/header.layout.html',
							footer: 'templates/layout/footer.layout.html'
						}
					},
					styles: {
						all: ['css/dist.css']
					}
				}
			}
		},
		concat: {
			js_dev: {
				src: [
                    'lib/vendor/angular/angular.js',
                    'lib/vendor/angular-animate/angular-animate.js',
                    'lib/vendor/angular-sanitize/angular-sanitize.js',
                    'lib/vendor/angular-route/angular-route.js',
                    'lib/vendor/angular-touch/angular-touch.js',
                    'lib/vendor/angular-ui-bootstrap/ui-bootstrap-tpls.js',
                    'lib/vendor/angular-ui-router/angular-ui-router.js',
                    'lib/vendor/tether/js/tether.js',
                    'lib/vendor/bootstrap/js/bootstrap.js',
                    'lib/vendor/angularjs-toaster/toaster.js',
					'./js/**/*.js',
                    '!./js/dist.js'
                ],
				dest: 'js/dist.js'
			},
			js_prod: {
				src: [
                    'lib/vendor/angular/angular.min.js',
                    'lib/vendor/angular-animate/angular-animate.min.js',
                    'lib/vendor/angular-sanitize/angular-sanitize.min.js',
                    'lib/vendor/angular-route/angular-route.min.js',
                    'lib/vendor/angular-touch/angular-touch.min.js',
                    'lib/vendor/angular-ui-bootstrap/ui-bootstrap-tpls.js',
                    'lib/vendor/angular-ui-router/angular-ui-router.min.js',
                    'lib/vendor/tether/js/tether.min.js',
                    'lib/vendor/bootstrap/js/bootstrap.min.js',
                    'lib/vendor/angularjs-toaster/toaster.min.js',
					'./js/**/*.js',
                    '!./js/dist.js'
                ],
				dest: 'js/dist.js'
			},
			css_client: {
				src: [
                    './css/*.css',
                    '!./css/dist.css'
                ],
				dest: './css/dist.css'
			},
			css_dist: {
				src: [
					'lib/vendor/bootstrap/css/bootstrap.min.css',
					'lib/vendor/angularjs-toaster/toaster.min.css',
					'lib/vendor/font-awesome/font-awesome.min.css',
                    './css/dist.css'
                ],
				dest: './css/dist.css'
			}
		},
		ngAnnotate: {
			options: {
				singleQuotes: true
			},
			dist: {
				files: {
					'js/dist.js': ['./js/dist.js']
				}
			}
		},
		uglify: {
			options: {
				mangle: false,
				preserveComments: false
			},
			dist: {
				files: {
					'./js/dist.js': ['./js/dist.js']
				}
			}
		},
		cssmin: {
			options: {
				shorthandCompacting: false,
				roundingPrecision: -1
			},
			target: {
				files: {
					'./css/dist.css': ['./css/dist.css']
				}
			}
		},
		htmlmin: {
			prod: {
				options: {
					removeComments: true,
					collapseWhitespace: true
				},
				files: [
					{
						expand: true,
						cwd: 'partials/',
						src: '**/*.html',
						dest: 'prod/partials'
					}, {
						src: 'index.html',
						dest: 'prod/index.html'
					}
				]
			}
		},
		clean: {
			js: ['./js/dist.js'],
			css: ['./css/dist.css'],
			lib: ['./lib/vendor'],
			html: ['./index.html'],
			prod: ['./prod']
		},
		jshint: {
			fileToFile: [
				'./js/**/*.js',
                '!./js/dist.js'
			],
			generated: [
				'./js/dist.js'
			]
		},
		watch: {
			scripts: {
				files: ['./js/**/*.js', '!./js/dist.js'],
				tasks: ['js_dev']
			},
			css: {
				files: ['./css/*.css', '!./css/dist.css'],
				tasks: ['css_dev']
			},
			html: {
				files: ['templates/index.tpl.html', 'templates/layout/*.html', 'partials/**/*.html'],
				tasks: ['html']
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
	grunt.loadNpmTasks('grunt-html-build');
	grunt.loadNpmTasks('grunt-contrib-concat');
	grunt.loadNpmTasks('grunt-ng-annotate');
	grunt.loadNpmTasks('grunt-contrib-uglify');
	grunt.loadNpmTasks('grunt-contrib-cssmin');
	grunt.loadNpmTasks('grunt-contrib-htmlmin');
	grunt.loadNpmTasks('grunt-contrib-clean');
	grunt.loadNpmTasks('grunt-contrib-jshint');
	grunt.loadNpmTasks('grunt-contrib-watch');

	// Définition des tâches Grunt
	grunt.registerTask('js_dev', ['clean:js', 'jshint:fileToFile', 'concat:js_dev']);
	grunt.registerTask('js_prod', ['clean:js', 'jshint:fileToFile', 'concat:js_prod', 'ngAnnotate:dist', 'uglify:dist']);
	grunt.registerTask('css_dev', ['clean:css', 'concat:css_client', 'concat:css_dist']);
	grunt.registerTask('css_prod', ['clean:css', 'concat:css_client', 'cssmin', 'concat:css_dist']);
	grunt.registerTask('html', ['clean:html', 'htmlbuild:index']);
	grunt.registerTask('move_vendor', ['copy:jquery', 'copy:angular', 'copy:angular_animate', 'copy:angular_sanitize', 'copy:angular_route', 'copy:angular_touch', 'copy:angular_ui_bootstrap', 'copy:angular_ui_router', 'copy:angular_ui_bootstrap_tpl', 'copy:tether', 'copy:bootstrap', 'copy:angularjs_toaster', 'copy:font_awesome', 'copy:font_awesome_fonts']);

	grunt.registerTask('dev', ['clean:lib', 'move_vendor', 'css_dev', 'js_dev', 'html']);
	grunt.registerTask('prod', ['clean:lib', 'move_vendor', 'css_prod', 'js_prod', 'html', 'clean:prod', 'copy:prod', 'htmlmin:prod']);

}
